package pt.ncastro.markettradeprocessor.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.Query;
import org.hibernate.Session;

import pt.ncastro.markettradeprocessor.shared.Country;
import pt.ncastro.markettradeprocessor.util.domainHelpers.TradeStatisticsDTO;
import pt.ncastro.markettradeprocessor.util.hibernate.HibernateUtil;



/**
 * This class runs the statistics computation for the trades in runtime. It
 * features a periodical job to update those statistics
 * 
 * @author Nuno de Castro
 *
 */
public class StatisticsCalculatorJob implements ServletContextListener {

	private static final Logger log = Logger.getLogger(StatisticsCalculatorJob.class.getName());
	private static final int JOB_PERIOD = 10; // in seconds

	private static final TradeStatisticsDTO stats = new TradeStatisticsDTO();
	private static final ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(1);


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet
	 * .ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		scheduler.scheduleAtFixedRate(new StatsCalcJob(), JOB_PERIOD, JOB_PERIOD, TimeUnit.SECONDS);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
	 * ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		scheduler.shutdown();
	}



	/**
	 * The statistics calculation job.
	 * 
	 * @author Nuno de Castro
	 *
	 */
	private class StatsCalcJob implements Runnable {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			Session session = null;
			try {
				session = HibernateUtil.getCurrentSessionWithActiveTransaction();

				// statistics we are getting:
				// -> last 5 minutes
				//    1) trade number
				//    2) top 5 trading countries
				//    3) num sell above 1000.0
				//    4) num buys above 1000.0
				// -> all time
				//    1) trade number
				//    2) top 5 trading countries
				//    3) top sell value
				//    4) top buy value

				// the date since we want to make countings
				Date timePlaced5Min = new Date(System.currentTimeMillis() - 5 * 60 * 1000);

				Query query;
				StringBuffer hql;
				// all time
				hql = new StringBuffer(128);
				hql.append(" select originatingCountry, count(*) as ct from Trade ");
				hql.append("  group by originatingCountry order by ct desc ");
				query = session.createQuery(hql.toString());
				query.setMaxResults(5);
				@SuppressWarnings("unchecked")
				List<Object[]> top5CAllTime = query.list();

				query = session.createQuery(" select count(*), max(amountSell), max(amountBuy) from Trade ");
				Object allTime[] = (Object[]) query.uniqueResult();

				// last 5 min
				hql = new StringBuffer(128);
				hql.append(" select originatingCountry, count(*) as ct from Trade ");
				hql.append("  where timePlaced > :timePlaced group by originatingCountry order by ct desc ");
				query = session.createQuery(hql.toString());
				query.setTimestamp("timePlaced", timePlaced5Min);
				query.setMaxResults(5);
				@SuppressWarnings("unchecked")
				List<Object[]> top5C5Min = query.list();

				query =
						session.createQuery(" select count(*) from Trade where amountSell > :value and timePlaced > :timePlaced ");
				query.setDouble("value", 1000.0);
				query.setTimestamp("timePlaced", timePlaced5Min);
				Number sell5Min = (Number) query.uniqueResult();

				query =
						session.createQuery(" select count(*) from Trade where amountBuy > :value and timePlaced > :timePlaced ");
				query.setDouble("value", 1000.0);
				query.setTimestamp("timePlaced", timePlaced5Min);
				Number buy5Min = (Number) query.uniqueResult();

				query = session.createQuery(" select count(*) from Trade where timePlaced > :timePlaced ");
				query.setTimestamp("timePlaced", timePlaced5Min);
				Number tradeNum5 = (Number) query.uniqueResult();

				// update the stats
				synchronized (stats) {
					// all time
					Number topSell = ((Number) allTime[1]);
					Number topBuy = ((Number) allTime[2]);
					stats.setTradeNum(((Number) allTime[0]).longValue());
					stats.setTopSellValue(topSell != null ? topSell.doubleValue() : 0.0);
					stats.setTopBuyValue(topBuy != null ? topBuy.doubleValue() : 0.0);
					Map<Country, Long> top5 = new HashMap<Country, Long>();
					for (Object[] o : top5CAllTime) {
						top5.put((Country) o[0], ((Number) o[1]).longValue());
					}
					stats.setTop5Countries(top5);

					// last 5 minutes
					stats.setTradeNum5Min(tradeNum5.intValue());
					stats.setNumSellAbove1000(sell5Min.intValue());
					stats.setNumBuyAbove1000(buy5Min.intValue());
					Map<Country, Integer> top55M = new HashMap<Country, Integer>();
					for (Object[] o : top5C5Min) {
						top55M.put((Country) o[0], ((Number) o[1]).intValue());
					}
					stats.setTop5Countries5Min(top55M);

					// create the related graphs
					stats.buildGraphs();
				}

			} catch (Exception e) {
				log.severe("Error updating trade statistics");

			} finally {
				if (session != null && session.getTransaction().isActive()) {
					session.getTransaction().rollback();
				}
			}
		}
	}


	/**
	 * Returns the current statistics in the form of a JSON object.
	 * 
	 * @return
	 */
	public static TradeStatisticsDTO getCurrentStatistics() {
		return stats;
	}
}

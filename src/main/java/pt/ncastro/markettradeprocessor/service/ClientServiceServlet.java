package pt.ncastro.markettradeprocessor.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.json.JSONObject;

import pt.ncastro.markettradeprocessor.domain.Trade;
import pt.ncastro.markettradeprocessor.util.domainHelpers.TradeStatisticsDTO;
import pt.ncastro.markettradeprocessor.util.hibernate.HibernateUtil;
import pt.ncastro.markettradeprocessor.util.json.JSONUtils;



/**
 * The client service servlet. This is the one that serves the frontend client.
 * 
 * @author Nuno de Castro
 *
 */
public class ClientServiceServlet extends HttpServlet {

	// Serial version ID.
	private static final long serialVersionUID = 6542009201892502398L;
	// Logging object
	private static final Logger log = Logger.getLogger(ClientServiceServlet.class.getName());


	/**
	 * Default constructor.
	 */
	public ClientServiceServlet() {
		super();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("Receiving a POST message");

		try {
			JSONObject json = JSONUtils.getJSONObject(req);

			// check method
			if (json != null) {
				String method = json.getString("method");
				if (method != null) {
					if (method.equals("getLastTrades")) {
						int numTrades = json.getInt("numTrades");
						List<Trade> trades = getLastTrades(numTrades);

						List<String> tradeDTOs = new ArrayList<String>();
						JSONObject res = new JSONObject();
						for (Trade t : trades) {
							tradeDTOs.add(t.toDTOClass().toJSON());
						}
						res.put("list", tradeDTOs);
						JSONUtils.sendJSONObject(resp, res);

					} else if (method.equals("getStatistics")) {
						TradeStatisticsDTO stats = StatisticsCalculatorJob.getCurrentStatistics();
						synchronized (stats) {
							JSONUtils.sendJSONContent(resp, stats.toJSON());
						}
					}
				}
			}

		} catch (Exception e) {
			log.severe(e.getMessage());
			throw new RuntimeException(e);
		}
	}



	/**
	 * Returns a list with the N-last trades in the DB.
	 * 
	 * @param nLast
	 * @return
	 */
	private List<Trade> getLastTrades(int nLast) {
		log.info("Getting the " + nLast + " last trades");

		Session session = null;
		try {
			session = HibernateUtil.getCurrentSessionWithActiveTransaction();

			String hql = " from Trade t order by t.timePlaced desc ";
			Query query = session.createQuery(hql);
			query.setMaxResults(nLast);

			@SuppressWarnings("unchecked")
			List<Trade> res = query.list();
			return res;

		} catch (Exception e) {
			log.log(Level.SEVERE, "Error getting the " + nLast + " last trades.", e);
			throw new RuntimeException(e);

		} finally {
			if (session != null && session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
		}
	}
}
package pt.ncastro.markettradeprocessor.service;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import pt.ncastro.markettradeprocessor.domain.Trade;
import pt.ncastro.markettradeprocessor.util.domainHelpers.TradeDTO;
import pt.ncastro.markettradeprocessor.util.hibernate.HibernateUtil;
import pt.ncastro.markettradeprocessor.util.json.JSONUtils;



/**
 * The trade service servlet. This is the one that takes external POST JSON
 * messages and prepares data for later processing and showing to client.
 * 
 * @author Nuno de Castro
 *
 */
public class TradeServiceServlet extends HttpServlet {

	// Serial version ID.
	private static final long serialVersionUID = 5872009201892502398L;
	// Logging object
	private static final Logger log = Logger.getLogger(TradeServiceServlet.class.getName());


	/**
	 * Default constructor.
	 */
	public TradeServiceServlet() {
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

		Session session = null;
		try {
			String content = JSONUtils.getJSONContent(req);

			// convert to DTO object
			TradeDTO tradeDTO = new TradeDTO();
			tradeDTO.fromJSON(content);

			// save data onto DB
			Trade trade = new Trade();
			trade.fromDTOClass(tradeDTO);

			session = HibernateUtil.getCurrentSessionWithActiveTransaction();
			session.save(trade);
			session.getTransaction().commit();

		} catch (Exception e) {
			// close the transaction if was created
			if (session != null && session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			log.severe(e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
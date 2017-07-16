package pt.ncastro.markettradeprocessor.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import pt.ncastro.markettradeprocessor.shared.Country;
import pt.ncastro.markettradeprocessor.shared.Currency;
import pt.ncastro.markettradeprocessor.util.domainHelpers.TradeDTO;



/**
 * The trade service servlet tester. This class should implement JUnit testing
 * to check the integration with JSON data posts. It's rather late today, so
 * I've skipped this testing implementation and implemented a manual tester that
 * was used during implementation.
 * 
 * @author Nuno de Castro
 *
 */
// TODO
public class TradeServiceServletTest {


	public static void main(String[] args) {
		try {

			// burst of 1000 trades
			for (int i = 0; i < 1000; i++) {

				// create a dummy trade
				TradeDTO trade = new TradeDTO();
				trade.setUserId((int) (Math.random() * 100000));
				trade.setCurrencyFrom(Currency.values()[(int) (Math.random() * Currency.values().length)]);
				trade.setCurrencyTo(Currency.values()[(int) (Math.random() * Currency.values().length)]);
				trade.setAmountSell(Math.random() * 2000.0);
				trade.setAmountBuy(Math.random() * 2000.0);
				trade.setRate(Math.random() * 2000.0);
				trade.setTimePlaced(new Date());
				trade.setOriginatingCountry(Country.values()[(int) (Math.random() * Country.values().length)]);

				// send it to server
				try {
					URL url = new URL("https://mkttradeprocessor.herokuapp.com/ts");
					URLConnection connection = url.openConnection();
					connection.setDoOutput(true);
					connection.setRequestProperty("Content-Type", "application/json");
					connection.setConnectTimeout(50000);
					connection.setReadTimeout(50000);
					OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
					out.write(trade.toJSON());
					out.close();

					BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					while (in.readLine() != null) {
					}
					System.out.println("Sucessfully sent data to server\n");
					in.close();
				} catch (Exception e) {
					System.out.println("Error sending data to server\n");
					System.out.println(e);
				}

				Thread.sleep((int) (Math.random() * 500));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
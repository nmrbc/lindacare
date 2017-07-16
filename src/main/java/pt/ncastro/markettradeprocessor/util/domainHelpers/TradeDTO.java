package pt.ncastro.markettradeprocessor.util.domainHelpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import pt.ncastro.markettradeprocessor.shared.Country;
import pt.ncastro.markettradeprocessor.shared.Currency;
import pt.ncastro.markettradeprocessor.util.domainHelpers.interfaces.IExchangeableByJSON;



/**
 * This is the class that is responsible for converting the JSON data into an
 * application-friendly object. This could be later on put into a separate
 * project, i.e., Integrations, in order to be decoupled from our main
 * application.
 * 
 * @author Nuno de Castro
 *
 */
public class TradeDTO implements IExchangeableByJSON {

	// JSON field names
	private static final String userId_prop = "userId";
	private static final String currencyFrom_prop = "currencyFrom";
	private static final String currencyTo_prop = "currencyTo";
	private static final String amountSell_prop = "amountSell";
	private static final String amountBuy_prop = "amountBuy";
	private static final String rate_prop = "rate";
	private static final String timePlaced_prop = "timePlaced";
	private static final String originatingCountry_prop = "originatingCountry";

	// date format
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy HH:mm:ss");

	// trade fields
	private int userId;
	private Currency currencyFrom;
	private Currency currencyTo;
	private double amountSell;
	private double amountBuy;
	private double rate;
	private Date timePlaced;
	private Country originatingCountry;



	/**
	 * Default constructor.
	 */
	public TradeDTO() {
		super();

		userId = 0;
		currencyFrom = null;
		currencyTo = null;
		amountSell = 0.0;
		amountBuy = 0.0;
		rate = 0.0;
		timePlaced = null;
		originatingCountry = null;
	}


	/**
	 * Returns the user id.
	 * 
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}


	/**
	 * Sets the user id.
	 * 
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}


	/**
	 * Returns the currency from where the trade was started.
	 * 
	 * @return the currencyFrom
	 */
	public Currency getCurrencyFrom() {
		return currencyFrom;
	}


	/**
	 * Sets the currency from where the trade was started.
	 * 
	 * @param currencyFrom the currencyFrom to set
	 */
	public void setCurrencyFrom(Currency currencyFrom) {
		this.currencyFrom = currencyFrom;
	}


	/**
	 * Returns the currency to where the trade was ended.
	 * 
	 * @return the currencyTo
	 */
	public Currency getCurrencyTo() {
		return currencyTo;
	}


	/**
	 * Seta the currency to where the trade was ended.
	 * 
	 * @param currencyTo the currencyTo to set
	 */
	public void setCurrencyTo(Currency currencyTo) {
		this.currencyTo = currencyTo;
	}


	/**
	 * Returns the number of stocks to be sold.
	 * 
	 * @return the amountSell
	 */
	public double getAmountSell() {
		return amountSell;
	}


	/**
	 * Sets the number of stocks to be sold.
	 * 
	 * @param amountSell the amountSell to set
	 */
	public void setAmountSell(double amountSell) {
		this.amountSell = amountSell;
	}


	/**
	 * Returns the number of stocks to be bought.
	 * 
	 * @return the amountBuy
	 */
	public double getAmountBuy() {
		return amountBuy;
	}


	/**
	 * Sets the number of stocks to be bought.
	 * 
	 * @param amountBuy the amountBuy to set
	 */
	public void setAmountBuy(double amountBuy) {
		this.amountBuy = amountBuy;
	}


	/**
	 * Returns the rate.
	 * 
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}


	/**
	 * Sets the rate.
	 * 
	 * @param rate the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}


	/**
	 * Returns the trade placement time.
	 * 
	 * @return the timePlaced
	 */
	public Date getTimePlaced() {
		return timePlaced;
	}


	/**
	 * Sets the trade placement time.
	 * 
	 * @param timePlaced the timePlaced to set
	 */
	public void setTimePlaced(Date timePlaced) {
		this.timePlaced = timePlaced;
	}


	/**
	 * Returns the country of origin of the trade.
	 * 
	 * @return the originatingCountry
	 */
	public Country getOriginatingCountry() {
		return originatingCountry;
	}


	/**
	 * Sets the country of origin of the trade.
	 * 
	 * @param originatingCountry the originatingCountry to set
	 */
	public void setOriginatingCountry(Country originatingCountry) {
		this.originatingCountry = originatingCountry;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see pt.ncastro.markettradeprocessor.util.domainHelpers.interfaces.
	 * IExchangeableByJSON#toJSON()
	 */
	@Override
	public String toJSON() throws JSONException {
		JSONObject s = new JSONObject();
		s.put(userId_prop, getUserId());
		s.put(currencyFrom_prop, getCurrencyFrom() != null ? getCurrencyFrom().getValue() : null);
		s.put(currencyTo_prop, getCurrencyTo() != null ? getCurrencyTo().getValue() : null);
		s.put(amountSell_prop, getAmountSell());
		s.put(amountBuy_prop, getAmountBuy());
		s.put(rate_prop, getRate());

		String date = null;
		Date timePlaced = getTimePlaced();
		if (timePlaced != null) {
			date = dateFormat.format(timePlaced);
		}
		s.put(timePlaced_prop, date);
		s.put(originatingCountry_prop, getOriginatingCountry() != null ? getOriginatingCountry().getISOCode() : null);
		return s.toString();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see pt.ncastro.markettradeprocessor.util.domainHelpers.interfaces.
	 * IExchangeableByJSON#fromJSON(java.lang.String)
	 */
	@Override
	public void fromJSON(String json) throws JSONException {
		JSONObject s = new JSONObject(json);
		setUserId(s.getInt(userId_prop));
		setCurrencyFrom(Currency.fromValue(s.getString(currencyFrom_prop)));
		setCurrencyTo(Currency.fromValue(s.getString(currencyTo_prop)));
		setAmountSell(s.getDouble(amountSell_prop));
		setAmountBuy(s.getDouble(amountBuy_prop));
		setRate(s.getDouble(rate_prop));

		Date timePlaced = null;
		String date = s.getString(timePlaced_prop);
		if (date != null) {
			try {
				timePlaced = dateFormat.parse(date);
			} catch (ParseException e) {
				throw new JSONException("Error parsing date. Not in standard format");
			}
		}
		setTimePlaced(timePlaced);
		setOriginatingCountry(Country.fromISOCode(s.getString(originatingCountry_prop)));
	}


}

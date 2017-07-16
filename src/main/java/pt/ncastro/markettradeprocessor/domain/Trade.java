package pt.ncastro.markettradeprocessor.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pt.ncastro.markettradeprocessor.shared.Country;
import pt.ncastro.markettradeprocessor.shared.Currency;
import pt.ncastro.markettradeprocessor.util.domainHelpers.TradeDTO;
import pt.ncastro.markettradeprocessor.util.domainHelpers.interfaces.IIntegrationForClass;



@Entity
@Table(name = "trade")
public class Trade implements Serializable, IIntegrationForClass<TradeDTO> {

	private static final long serialVersionUID = -3071722604891631890L;

	// table fields
	private int id;
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
	public Trade() {
		super();
		reset();
	}


	/**
	 * Resets the structure data.
	 */
	private void reset() {
		id = 0;
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
	 * Returns the id.
	 * 
	 * @return the userId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return id;
	}


	/**
	 * Sets the id.
	 * 
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * Returns the user id.
	 * 
	 * @return the userId
	 */
	@Column(name = "user_id", nullable = false)
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
	@Enumerated(EnumType.STRING)
	@Column(name = "currencyfrom", nullable = false)
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
	@Enumerated(EnumType.STRING)
	@Column(name = "currencyto", nullable = false)
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
	@Column(name = "amountsell", nullable = false)
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
	@Column(name = "amountbuy", nullable = false)
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
	@Column(name = "rate", nullable = false)
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
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "timeplaced", nullable = false)
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
	@Enumerated(EnumType.STRING)
	@Column(name = "originatingcountry", nullable = false)
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
	 * IIntegrationForClass#toDTOClass()
	 */
	@Override
	public TradeDTO toDTOClass() {
		TradeDTO trade = new TradeDTO();
		trade.setUserId(getUserId());
		trade.setCurrencyFrom(getCurrencyFrom());
		trade.setCurrencyTo(getCurrencyTo());
		trade.setAmountSell(getAmountSell());
		trade.setAmountBuy(getAmountBuy());
		trade.setRate(getRate());
		trade.setTimePlaced(getTimePlaced());
		trade.setOriginatingCountry(getOriginatingCountry());
		return trade;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see pt.ncastro.markettradeprocessor.util.domainHelpers.interfaces.
	 * IIntegrationForClass
	 * #fromDTOClass(pt.ncastro.markettradeprocessor.util.domainHelpers
	 * .interfaces.IExchangeableByJSON)
	 */
	@Override
	public void fromDTOClass(TradeDTO dto) {
		reset();
		if (dto != null) {
			setUserId(dto.getUserId());
			setCurrencyFrom(dto.getCurrencyFrom());
			setCurrencyTo(dto.getCurrencyTo());
			setAmountSell(dto.getAmountSell());
			setAmountBuy(dto.getAmountBuy());
			setRate(dto.getRate());
			setTimePlaced(dto.getTimePlaced());
			setOriginatingCountry(dto.getOriginatingCountry());
		}
	}
}

package pt.ncastro.markettradeprocessor.shared;

/**
 * These are the available currencies to be handled by the application. New
 * currencies could be added here.
 * 
 * @author Nuno de Castro
 *
 */
public enum Currency {
	USD,
	EUR,
	JPY,
	GBP,
	CHF,
	CAD,
	AUD,
	ZAR,

	;

	/**
	 * Returns the currency name.
	 * 
	 * @return
	 */
	public String getValue() {
		return name();
	}


	/**
	 * Returns the Currency enum given its value.
	 * 
	 * @param value
	 * @return
	 */
	public static Currency fromValue(String value) {
		return Currency.valueOf(value);
	}
}

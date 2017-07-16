package pt.ncastro.markettradeprocessor.util.domainHelpers;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pt.ncastro.markettradeprocessor.shared.Country;
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
public class TradeStatisticsDTO implements IExchangeableByJSON {

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

	// JSON field names
	private static final String tradeNum_prop = "tn";
	private static final String top5Countries_prop = "t5c";
	private static final String numSellAbove1000_prop = "nsa";
	private static final String numBuyAbove1000_prop = "nba";
	private static final String tradeNum5Min_prop = "tn5";
	private static final String top5Countries5Min_prop = "t5c5";
	private static final String topSellValue_prop = "tsv";
	private static final String topBuyValue_prop = "tbv";
	private static final String graphs_prop = "grp";

	// trade statistics fields
	private long tradeNum;
	private Map<Country, Long> top5Countries;
	private long numSellAbove1000;
	private long numBuyAbove1000;
	private int tradeNum5Min;
	private Map<Country, Integer> top5Countries5Min;
	private double topSellValue;
	private double topBuyValue;
	private String graphs;



	/**
	 * Default constructor.
	 */
	public TradeStatisticsDTO() {
		super();
		reset();
	}


	/**
	 * Resets the structure data.
	 */
	private void reset() {
		tradeNum = 0;
		top5Countries = new HashMap<Country, Long>();
		numSellAbove1000 = 0;
		numBuyAbove1000 = 0;
		tradeNum5Min = 0;
		top5Countries5Min = new HashMap<Country, Integer>();
		topSellValue = 0.0;
		topBuyValue = 0.0;
		graphs = null;
	}


	/**
	 * @return the tradeNum
	 */
	public long getTradeNum() {
		return tradeNum;
	}


	/**
	 * @param tradeNum the tradeNum to set
	 */
	public void setTradeNum(long tradeNum) {
		this.tradeNum = tradeNum;
	}


	/**
	 * @return the top5Countries
	 */
	public Map<Country, Long> getTop5Countries() {
		return top5Countries;
	}


	/**
	 * @param top5Countries the top5Countries to set
	 */
	public void setTop5Countries(Map<Country, Long> top5Countries) {
		this.top5Countries = top5Countries;
	}


	/**
	 * @return the numSellAbove1000
	 */
	public long getNumSellAbove1000() {
		return numSellAbove1000;
	}


	/**
	 * @param numSellAbove1000 the numSellAbove1000 to set
	 */
	public void setNumSellAbove1000(long numSellAbove1000) {
		this.numSellAbove1000 = numSellAbove1000;
	}


	/**
	 * @return the numBuyAbove1000
	 */
	public long getNumBuyAbove1000() {
		return numBuyAbove1000;
	}


	/**
	 * @param numBuyAbove1000 the numBuyAbove1000 to set
	 */
	public void setNumBuyAbove1000(long numBuyAbove1000) {
		this.numBuyAbove1000 = numBuyAbove1000;
	}


	/**
	 * @return the tradeNum5Min
	 */
	public int getTradeNum5Min() {
		return tradeNum5Min;
	}


	/**
	 * @param tradeNum5Min the tradeNum5Min to set
	 */
	public void setTradeNum5Min(int tradeNum5Min) {
		this.tradeNum5Min = tradeNum5Min;
	}


	/**
	 * @return the top5Countries5Min
	 */
	public Map<Country, Integer> getTop5Countries5Min() {
		return top5Countries5Min;
	}


	/**
	 * @param top5Countries5Min the top5Countries5Min to set
	 */
	public void setTop5Countries5Min(Map<Country, Integer> top5Countries5Min) {
		this.top5Countries5Min = top5Countries5Min;
	}


	/**
	 * @return the topSellValue
	 */
	public double getTopSellValue() {
		return topSellValue;
	}


	/**
	 * @param topSellValue the topSellValue to set
	 */
	public void setTopSellValue(double topSellValue) {
		this.topSellValue = topSellValue;
	}


	/**
	 * @return the topBuyValue
	 */
	public double getTopBuyValue() {
		return topBuyValue;
	}


	/**
	 * @param topBuyValue the topBuyValue to set
	 */
	public void setTopBuyValue(double topBuyValue) {
		this.topBuyValue = topBuyValue;
	}


	/**
	 * @return the graphs
	 */
	public String getGraphs() {
		return graphs;
	}


	/**
	 * @param topBuyValue the topBuyValue to set
	 */
	public void setGraphs(String graphs) {
		this.graphs = graphs;
	}


	/**
	 * Builds the graphs data.
	 */
	public void buildGraphs() {
		double tn = getTradeNum();
		double tn5 = getTradeNum5Min();
		Map<Country, Long> top5 = getTop5Countries();
		Map<Country, Integer> top55M = getTop5Countries5Min();
		double max = 0.0, max5 = 0.0;
		double scale[] = { 25, 50, 75, 100 }, scale5[] = { 25, 50, 75, 100 };
		String colors[] = { "red", "green", "blue", "orange", "cyan" };

		// get the biggest value for each case
		for (Long val : top5.values()) {
			if (val.doubleValue() / tn > max) {
				max = val.doubleValue() / tn;
			}
		}
		for (Integer val : top55M.values()) {
			if (val.doubleValue() / tn5 > max5) {
				max5 = val.doubleValue() / tn5;
			}
		}
		processScale(max, scale);
		processScale(max5, scale5);

		StringBuffer graphs = new StringBuffer(1024);
		graphs.append("<svg version=\"1.1\" id=\"Layer_1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"");
		graphs.append("x=\"0px\" y=\"0px\" width=\"100%\" height=\"100%\" viewBox=\"0 0 1024 512\" enable-background=\"new 0 0 1024 512\"");
		graphs.append("xml:space=\"preserve\" style=\"background: white; color:black;\">");
		graphs.append("	<text x='40' y='30' fill='black'>Top 5 trading countries (All Time)</text>");
		graphs.append("	<text x='10' y='60' fill='black'>%</text>");
		graphs.append("	<line x1='30' y1='60' x2='30' y2='482' style='stroke:rgb(0,0,0);stroke-width:2' />");
		graphs.append("	<line x1='30' y1='482' x2='482' y2='482' style='stroke:rgb(0,0,0);stroke-width:2' />");
		graphs.append("	<line x1='25' y1='382' x2='35' y2='382' style='stroke:rgb(0,0,0);stroke-width:1' />");
		graphs.append("	<line x1='25' y1='282' x2='35' y2='282' style='stroke:rgb(0,0,0);stroke-width:1' />");
		graphs.append("	<line x1='25' y1='182' x2='35' y2='182' style='stroke:rgb(0,0,0);stroke-width:1' />");
		graphs.append("	<line x1='25' y1='82' x2='35' y2='82' style='stroke:rgb(0,0,0);stroke-width:1' />");
		graphs.append("	<text x='0' y='388' fill='black'>" + scale[0] + "</text>");
		graphs.append("	<text x='0' y='288' fill='black'>" + scale[1] + "</text>");
		graphs.append("	<text x='0' y='188' fill='black'>" + scale[2] + "</text>");
		graphs.append("	<text x='0' y='88' fill='black'>" + scale[3] + "</text>");

		graphs.append("	<text x='532' y='30' fill='black'>Top 5 trading countries (Last 5 minutes)</text>");
		graphs.append("	<text x='502' y='60' fill='black'>%</text>");
		graphs.append("	<line x1='522' y1='60' x2='522' y2='482' style='stroke:rgb(0,0,0);stroke-width:2' />");
		graphs.append("	<line x1='522' y1='482' x2='1014' y2='482' style='stroke:rgb(0,0,0);stroke-width:2' />");
		graphs.append("	<line x1='517' y1='382' x2='527' y2='382' style='stroke:rgb(0,0,0);stroke-width:1' />");
		graphs.append("	<line x1='517' y1='282' x2='527' y2='282' style='stroke:rgb(0,0,0);stroke-width:1' />");
		graphs.append("	<line x1='517' y1='182' x2='527' y2='182' style='stroke:rgb(0,0,0);stroke-width:1' />");
		graphs.append("	<line x1='517' y1='82' x2='527' y2='82' style='stroke:rgb(0,0,0);stroke-width:1' />");
		graphs.append("	<text x='492' y='388' fill='black'>" + scale5[0] + "</text>");
		graphs.append("	<text x='492' y='288' fill='black'>" + scale5[1] + "</text>");
		graphs.append("	<text x='492' y='188' fill='black'>" + scale5[2] + "</text>");
		graphs.append("	<text x='492' y='88' fill='black'>" + scale5[3] + "</text>");

		// build the bars for all time data
		int startX = 60;
		int i = 0;
		for (Country c : top5.keySet()) {
			max = 100 / scale[3] * top5.get(c).doubleValue() / tn * 400;
			graphs.append("	<rect x='"
					+ startX
					+ "' y='"
					+ (482 - max)
					+ "' width='50' height='"
					+ max
					+ "' style='fill:" + colors[i++] + ";'/>");
			graphs.append("	<text x='" + (startX + 15) + "' y='502' fill='black'>" + c.getISOCode() + "</text>");
			startX += 80;
		}
		// build the bars for 5 minute data
		startX = 552;
		i = 0;
		for (Country c : top55M.keySet()) {
			max = 100 / scale5[3] * top55M.get(c).doubleValue() / tn5 * 400;
			graphs.append("	<rect x='"
					+ startX
					+ "' y='"
					+ (482 - max)
					+ "' width='50' height='"
					+ max
					+ "' style='fill:" + colors[i++] + ";'/>");
			graphs.append("	<text x='" + (startX + 15) + "' y='502' fill='black'>" + c.getISOCode() + "</text>");
			startX += 80;
		}

		graphs.append("</svg>");
		setGraphs(graphs.toString());
	}


	/**
	 * Adapts the scale to the data to show.
	 * 
	 * @param max
	 * @param scale
	 */
	private void processScale(double max, double[] scale) {
		if (max < 0.025) {
			scale[0] = 2.5 * 0.25;
			scale[1] = 2.5 * 0.5;
			scale[2] = 2.5 * 0.75;
			scale[3] = 2.5;
		} else if (max < 0.05) {
			scale[0] = 5 * 0.25;
			scale[1] = 5 * 0.5;
			scale[2] = 5 * 0.75;
			scale[3] = 5;
		} else if (max < 0.1) {
			scale[0] = 10 * 0.25;
			scale[1] = 10 * 0.5;
			scale[2] = 10 * 0.75;
			scale[3] = 10;
		} else if (max < 0.25) {
			scale[0] = 25 * 0.25;
			scale[1] = 25 * 0.5;
			scale[2] = 25 * 0.75;
			scale[3] = 25;
		} else if (max < 0.5) {
			scale[0] = 50 * 0.25;
			scale[1] = 50 * 0.5;
			scale[2] = 50 * 0.75;
			scale[3] = 50;
		}
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
		s.put(tradeNum_prop, getTradeNum());
		s.put(numSellAbove1000_prop, getNumSellAbove1000());
		s.put(numBuyAbove1000_prop, getNumBuyAbove1000());
		s.put(tradeNum5Min_prop, getTradeNum5Min());
		s.put(topSellValue_prop, getTopSellValue());
		s.put(topBuyValue_prop, getTopBuyValue());

		// add the maps
		JSONArray t5cList = new JSONArray();
		t5cList.put(getTop5Countries());
		s.put(top5Countries_prop, t5cList);
		JSONArray t5c5List = new JSONArray();
		t5c5List.put(getTop5Countries5Min());
		s.put(top5Countries5Min_prop, t5c5List);

		// add the graphs
		s.put(graphs_prop, getGraphs());
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
		reset();
		if (json != null) {
			JSONObject s = new JSONObject(json);
			setTradeNum(s.getLong(tradeNum_prop));
			setNumSellAbove1000(s.getLong(numSellAbove1000_prop));
			setNumBuyAbove1000(s.getLong(numBuyAbove1000_prop));
			setTradeNum5Min(s.getInt(tradeNum5Min_prop));
			setTopSellValue(s.getDouble(topSellValue_prop));
			setTopBuyValue(s.getDouble(topBuyValue_prop));

			// set the maps
			JSONArray t5cList = s.getJSONArray(top5Countries_prop);
			setTop5Countries((Map<Country, Long>) t5cList.get(0));
			JSONArray t5c5List = s.getJSONArray(top5Countries5Min_prop);
			setTop5Countries5Min((Map<Country, Integer>) t5c5List.get(0));

			// set the graphs
			setGraphs(s.getString(graphs_prop));
		}
	}


}

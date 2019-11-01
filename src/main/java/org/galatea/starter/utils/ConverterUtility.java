package org.galatea.starter.utils;

import com.fasterxml.jackson.databind.JsonNode;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import org.apache.commons.lang3.time.DateUtils;
import org.galatea.starter.POJOs.StockPriceData;

public class ConverterUtility {

  public static List<StockPriceData> convertToStockPriceDataListFromJsonNode(JsonNode jsonNode)
      throws ParseException {
    List<StockPriceData> StockPriceDataList = new LinkedList<>();

    String stockTicker = jsonNode.path("Meta Data").path("2. Symbol").toString().replace("\"", "");

    JsonNode timeSeries = jsonNode.path("Time Series (Daily)");

    Iterator<Entry<String,JsonNode>> dateFields = timeSeries.fields();

    while(dateFields.hasNext()) {
      Entry<String, JsonNode> entry = dateFields.next();
      StockPriceData stockPriceData = convertToStockPriceDataFromJsonNode(stockTicker, entry);
      StockPriceDataList.add(stockPriceData);
    }
    return StockPriceDataList;
  }


  protected static StockPriceData convertToStockPriceDataFromJsonNode(String stockTicker, Entry<String, JsonNode> entry)
      throws ParseException {
    StockPriceData stockPriceData = new StockPriceData();

    JsonNode dateNode = entry.getValue();
    String dateString = entry.getKey();

    String tickerDateID = stockTicker + dateString.replaceAll("-", "");
    stockPriceData.set_id(tickerDateID);
    stockPriceData.setStockTicker(stockTicker);
    stockPriceData.setOpenPrice(Double.parseDouble(dateNode.path("1. open").toString().replace("\"", "")));
    stockPriceData.setClosePrice(Double.parseDouble(dateNode.path("4. close").toString().replace("\"", "")));
    Date date = DateUtils.parseDate(dateString, "yyyy-MM-dd");
    stockPriceData.setDate(date);

    return stockPriceData;
  }
}

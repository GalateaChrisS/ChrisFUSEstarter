package org.galatea.starter.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class AlphaVantageProxyService {

  private static final String alphaVantageBaseURL = "https://www.alphavantage.co/query";
  private static final String API_KEY = "FSXAXDTE8Z90P40B";

  /**
   * Fetches the pprice information of a given stock for the last 100 days from alphaVantage
   * @param stockTicker Ticker symbol of the stock being fetched
   * @return a string of the stock price infor for the last 100 days
   */
  public String get100DaysOfStockPrice(String stockTicker) {
    String UriString = buildUri(stockTicker);
    RestTemplate alphaVantage = new RestTemplate();
    ResponseEntity<String> result = alphaVantage.getForEntity(UriString,String.class);
    return result.getBody();
  }

  /**
   * Builds the uri string for a get request to AlphaVantage with correct parameters
   * @param stockTicker Ticker symbol of the stock being queried
   * @return String representation of full uri
   */
  public static String buildUri(String stockTicker) {
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(alphaVantageBaseURL)
      .queryParam("function", "TIME_SERIES_DAILY")
      .queryParam("symbol", stockTicker)
      .queryParam("apikey", API_KEY);
  return builder.toUriString();
}

}

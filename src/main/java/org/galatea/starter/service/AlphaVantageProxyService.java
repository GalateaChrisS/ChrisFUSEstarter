package org.galatea.starter.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.aspect4log.Log;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@Slf4j
@Log
@Service
public class AlphaVantageProxyService {

  private static final String alphaVantageBaseURL = "https://www.alphavantage.co/query";
  private static final String API_KEY = "FSXAXDTE8Z90P40B";

  /**
   * Fetches the pprice information of a given stock for the last 100 days from alphaVantage
   * @param stockTicker Ticker symbol of the stock being fetched
   * @return a string of the stock price infor for the last 100 days
   */
  public String fetch100DaysOfStockPrice(String stockTicker) {
    String UriString = buildUri(stockTicker);
    RestTemplate alphaVantage = new RestTemplate();
    ResponseEntity<JsonNode> result = alphaVantage.getForEntity(UriString,JsonNode.class);
    return result.getBody().toString();
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

package org.galatea.starter.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.aspect4log.Log;
import org.apache.commons.lang3.time.DateUtils;
import org.galatea.starter.POJOs.StockPriceData;
import org.galatea.starter.repositories.StockPriceRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Log
@Service
public class StockPriceLocatorService {

  @NonNull
  AlphaVantageProxyService alphaVantageProxyService;
  @NonNull
  StockPriceRepository stockPriceRepository;

  public List<StockPriceData> findStockPriceData(String stockTicker, int pastDays)
      throws ParseException {
    List<StockPriceData> stockPriceDataList = pullFromMongoDB(stockTicker, pastDays);
    if (stockPriceDataList.size()<pastDays) { //not enough cached data
      log.info("Not enough stock price data found, will fetch from alpha vantage");
      log.info("stockTicker = {}", stockTicker);
      alphaVantageProxyService.transferDataFetchedFromAlphaVantageToMongoDB(stockTicker);
      stockPriceDataList = pullFromMongoDB(stockTicker, pastDays);
    }
    return stockPriceDataList;
  }

  private List<StockPriceData> pullFromMongoDB(String stockTicker, int pastDays) {
    Date todaysDate = new Date();
    Date furthestBackDate = DateUtils.addDays(todaysDate, -pastDays);
    return stockPriceRepository.findByStockTickerAndDateBetween(stockTicker,furthestBackDate,todaysDate);
  }


}

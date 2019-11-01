package org.galatea.starter.entrypoint;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.aspect4log.Log;
import net.sf.aspect4log.Log.Level;
import org.galatea.starter.POJOs.StockPriceData;
import org.galatea.starter.service.AlphaVantageProxyService;
import org.galatea.starter.service.DummyService;
import org.galatea.starter.service.StockPriceFormatter;
import org.galatea.starter.service.StockPriceLocatorService;
import org.galatea.starter.utils.ConverterUtility;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller that listens to http endpoints and allows the caller to send text to be
 * processed.
 */
@RequiredArgsConstructor
@Slf4j
@Log(enterLevel = Level.INFO, exitLevel = Level.INFO)
@RestController
public class StockPriceController extends BaseRestController {

  @NonNull
  DummyService dummyService;
  @NonNull
  AlphaVantageProxyService alphaVantageProxyService;
  @NonNull
  StockPriceLocatorService stockPriceLocatorService;
  @NonNull
  StockPriceFormatter stockPriceFormatter;


  // @GetMapping to link http GET request to this method
  // @RequestParam to take a parameter from the url
  @GetMapping(value = "${webservice.dummyPath}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public String dummyEndpoint(
      @RequestParam(value = "command") String command,
      @RequestParam(value = "name", defaultValue = "user", required = false) String name,
      @RequestParam(value = "requestId", required = false) final String requestId) {
    processRequestId(requestId);
    return dummyService.processCommand(command, name);
  }

  // @GetMapping to link http GET request to this method
  // @RequestParam to take a parameter from the url
  @GetMapping(value = "${webservice.stockPricePath}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public String stockPriceEndpoint(
      @RequestParam(value = "stockTicker") String stockTicker,
      @RequestParam(value = "pastDays", defaultValue = "100", required = false) int pastDays,
      @RequestParam(value = "requestId", required = false) final String requestId)
      throws IOException, ParseException {
    processRequestId(requestId);
    List<StockPriceData> stockPriceDataList = stockPriceLocatorService.findStockPriceData(stockTicker, pastDays);
    return stockPriceFormatter.convertToStringFromStockPriceDataList(stockPriceDataList);
  }
}

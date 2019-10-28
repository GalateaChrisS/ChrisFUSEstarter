package org.galatea.starter.entrypoint;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.aspect4log.Log;
import net.sf.aspect4log.Log.Level;
import org.galatea.starter.service.DummyService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  // @GetMapping to link http GET request to this method
  // @RequestParam to take a parameter from the url
  @GetMapping(value = "${webservice.stockPricePath}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public String stockPriceEndpoint(
      @RequestParam(value = "command") String command,
      @RequestParam(value = "name") String name,
      @RequestParam(value = "requestId", required = false) final String requestId) {
    processRequestId(requestId);
    return dummyService.processCommand(command, name);
  }
}

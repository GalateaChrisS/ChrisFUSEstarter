package org.galatea.starter.entrypoint;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import junitparams.JUnitParamsRunner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.galatea.starter.ASpringTest;
import org.galatea.starter.service.DummyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@RequiredArgsConstructor
@Slf4j
// We don't load the entire spring application context for this test.
@WebMvcTest(StockPriceController.class)
// Use this runner since we want to parameterize certain tests.
// See runner's javadoc for more usage.
@RunWith(JUnitParamsRunner.class)
public class StockPriceControllerTest extends ASpringTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private DummyService mockDummyService;

  @Test
  public void testStockPriceEndpoint() throws Exception {
    String param1 = "command";
    String param1Val = "greet-me";
    String param2 = "name";
    String param2Val = "chris";

    String result = "command executed";

    given(this.mockDummyService.processCommand(param1Val, param2Val)).willReturn(result);

    this.mvc.perform(
        get("/stockprice").param(param1, param1Val).param(param2, param2Val).accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$", is(result)));
  }

}

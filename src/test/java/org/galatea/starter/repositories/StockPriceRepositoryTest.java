package org.galatea.starter.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import junitparams.JUnitParamsRunner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.galatea.starter.ASpringTest;
import org.galatea.starter.MongoConfig;
import org.galatea.starter.POJOs.StockPriceData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.galatea.starter.repositories.StockPriceRepository;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;

@RequiredArgsConstructor
@Slf4j
// We don't load the entire spring application context for this test.
@WebMvcTest(StockPriceRepository.class)
// Use this runner since we want to parameterize certain tests.
// See runner's javadoc for more usage.
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(classes={MongoConfig.class})
public class StockPriceRepositoryTest extends ASpringTest {

  @Autowired
  private StockPriceRepository repo;

  @Test
  public void testSaveStockPriceDataToRepo() {

    StockPriceData stock = new StockPriceData();
    stock.setStockTicker("AAPL");
    stock.setDate(new Date());
    System.out.println(stock);
    repo.save(stock);
  }

  @Test
  public void testExistsByByStockTickerAndDate() {

    StockPriceData stock = new StockPriceData();
    String stockTicker = "TSLA";
    Date date = new Date();

    stock.setStockTicker(stockTicker);
    stock.setDate(date);

    repo.save(stock);

    assertTrue(repo.existsByStockTickerAndDate(stockTicker, date));
  }

  @Test
  public void findFirstByByStockTickerAndDate() {

    StockPriceData stock = new StockPriceData();
    String stockTicker = "IBM";
    Date date = new Date();

    stock.setStockTicker(stockTicker);
    stock.setDate(date);

    repo.save(stock);

    assertEquals(stock, repo.findFirstByStockTickerAndDate(stockTicker,date));
  }
}

package org.galatea.starter.repositories;

import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.aspect4log.Log;
import net.sf.aspect4log.Log.Level;
import org.galatea.starter.POJOs.StockPriceData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Log(enterLevel = Level.INFO, exitLevel = Level.INFO)
@Repository
public interface StockPriceRepository extends MongoRepository<StockPriceData, String> {

  boolean existsByStockTickerAndDate(String stockTicker, Date date);
  List<StockPriceData> findByStockTickerAndDate(String stockTicker, Date date);
  List<StockPriceData> findByStockTickerAndDateBetween(String stockTicker, Date startDate, Date endDate);
  List<StockPriceData> findByStockTickerAndDateLessThan(String stockTicker, Date todaysDate);

}

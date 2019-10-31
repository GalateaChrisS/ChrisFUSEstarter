package org.galatea.starter.POJOs;

import java.util.Date;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter @Setter @ToString
@RequiredArgsConstructor
@Data
public class StockPriceData {
  @Id
  private String _id;
  @Field
  private String stockTicker;
  @Field
  private Date date;
  @Field
  private double openPrice;
  @Field
  private double closePrice;
}

package org.galatea.starter;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
public class MongoConfig extends AbstractMongoConfiguration {

//  @Autowired
//  private Environment env;

  @Override
  protected String getDatabaseName() {
    return "dev";
  }

  @Override
  public MongoClient mongoClient() {
    return new MongoClient("localhost", 27017);
  }

  @Override
  protected String getMappingBasePackage() {
    return "com.galatea.starter";
  }

}

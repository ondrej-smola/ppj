package javappj.Conf;

import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.test.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.transaction.PseudoTransactionManager;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Created by The CAT on 27.6.2016.
 */
@Profile("mongo")
@ImportAutoConfiguration({
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class,
        MongoRepositoriesAutoConfiguration.class})
@EnableMongoRepositories(basePackages = "javappj.data")
public class MongoConf {

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new PseudoTransactionManager();
    }
}

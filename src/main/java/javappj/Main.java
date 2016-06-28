package javappj;

import javappj.Conf.MongoConf;
import javappj.Conf.MysqlConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import java.util.Arrays;

@SpringBootApplication(exclude = {
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class,
        MongoRepositoriesAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        DataSourceAutoConfiguration.class,
        JpaRepositoriesAutoConfiguration.class})
@Import({MysqlConf.class, MongoConf.class})
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(Main.class);
        System.out.println("START1  SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
        ConfigurableApplicationContext context = app.run(args);
        System.out.println("START 2 SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");

        log.debug(Arrays.toString(context.getEnvironment().getActiveProfiles()));

    }

}
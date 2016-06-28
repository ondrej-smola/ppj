package javappj.Conf;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.test.ImportAutoConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by The CAT on 27.6.2016.
 */
@Profile({"test", "mysql"})
@ImportAutoConfiguration({
        HibernateJpaAutoConfiguration.class,
        DataSourceAutoConfiguration.class,
        JpaRepositoriesAutoConfiguration.class
})
@EnableJpaRepositories(basePackages = "javappj.data")
public class MysqlConf {

}

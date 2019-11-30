package khannanov.kvstorage.config;

import khannanov.kvstorage.model.Entry;
import khannanov.kvstorage.model.EntryChange;
import khannanov.kvstorage.model.User;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "khannanov.kvstorage.repository")
@ComponentScan(basePackages = {"khannanov.kvstorage.repository",
        "khannanov.kvstorage.service", "khannanov.kvstorage.web"})
@EntityScan(basePackages = {"khannanov.kvstorage.model"})
@PropertySource("db.properties")
public class JpaConfig {

    @Autowired
    private Environment environment;

    @Bean(name="entityManagerFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setPackagesToScan("khannanov.kvstorage");
        sessionFactory.setDataSource(dataSource());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getProperty("jdbc.url"));
        dataSource.setUsername(environment.getProperty("jdbc.username"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));

        return dataSource;
    }

}

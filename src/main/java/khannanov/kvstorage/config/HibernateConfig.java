package khannanov.kvstorage.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "khannanov.kvstorage.repository")
@EnableTransactionManagement
@PropertySource(value = "classpath:db.properties")
@PropertySource(value = "classpath:hibernate.properties")
public class HibernateConfig {

    static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    static final String HIBERNATE_DIALECT = "hibernate.dialect";
    static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    static final String HIBERNATE_TEMP_USE_JDBC_METADATE_DEFAULTS =  "hibernate.temp.use_jdbc_metadata_defaults";
    static final String JDBC_DRIVER_CLASS_NAME = "jdbc.driverClassName";
    static final String JDBC_URI = "jdbc.url";
    static final String JDBC_USERNAME = "jdbc.username";
    static final String JDBC_PASSWORD = "jdbc.password";
    private Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
         sessionFactory.setPackagesToScan("khannanov.kvstorage.model");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty(JDBC_DRIVER_CLASS_NAME));
        dataSource.setUrl(environment.getProperty(JDBC_URI));
        dataSource.setUsername(environment.getProperty(JDBC_USERNAME));
        dataSource.setPassword(environment.getProperty(JDBC_PASSWORD));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
          = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(HIBERNATE_HBM2DDL_AUTO, environment.getProperty(HIBERNATE_HBM2DDL_AUTO));
        hibernateProperties.setProperty(HIBERNATE_DIALECT, environment.getProperty(HIBERNATE_DIALECT));
        hibernateProperties.setProperty(HIBERNATE_SHOW_SQL, environment.getProperty(HIBERNATE_SHOW_SQL));
        hibernateProperties.setProperty(HIBERNATE_TEMP_USE_JDBC_METADATE_DEFAULTS,
                environment.getProperty(HIBERNATE_TEMP_USE_JDBC_METADATE_DEFAULTS));
        return hibernateProperties;
    }
}

package webmvc.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:/dataSource.properties")
public class DatabaseConfig {
    
    @Value("${datasource.driverClassName}")
    private String driverClassName;
    @Value("${datasource.url}")
    private String url;
    @Value("${datasource.username}")
    private String username;
    @Value("${datasource.password}")
    private String password;
    
    @Bean
    public DataSource dataSource() {
        DataSource ds = new DataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setInitialSize(2);
        ds.setMaxActive(10);
        ds.setMaxIdle(10);
        ds.setTestWhileIdle(true);
        ds.setMinEvictableIdleTimeMillis(3 * 60 * 1000);
        ds.setTimeBetweenEvictionRunsMillis(10 * 1000);
        return ds;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(dataSource());
        return tm;
    }
}

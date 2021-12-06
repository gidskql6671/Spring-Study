package jdbc.config;

import jdbc.dao.MemberDao;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value= "classpath:/dataSource.properties")
public class TestConfiguration {

    @Value("${datasource.driverClassName}")
    private String driverClassName;
    @Value("${datasource.testUrl}")
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
        return ds;
    }
    
    @Bean
    public MemberDao memberDao() {
        return new MemberDao(dataSource());
    }
}

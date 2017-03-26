package in.spinsoft.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan( basePackages="in.spinsoft")
@EnableWebMvc
public class AppConfig {

	@Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource)
    {
		System.out.println("Datasource  " + dataSource);
        return new JdbcTemplate(dataSource);
    }	
}

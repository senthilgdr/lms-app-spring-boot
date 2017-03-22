package in.spinsoft;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class LmsWebappApplication {

	@Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource)
    {
		System.out.println("Datasource" + dataSource);
        return new JdbcTemplate(dataSource);
    }	
	
	public static void main(String[] args) { 
		SpringApplication.run(LmsWebappApplication.class, args);
	}
	
	
	
}

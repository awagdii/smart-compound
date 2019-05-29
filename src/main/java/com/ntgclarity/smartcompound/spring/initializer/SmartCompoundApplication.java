package com.ntgclarity.smartcompound.spring.initializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement  
@Configuration   
@ImportResource(locations = "classpath:/spring/database/database.xml")  
@ComponentScan("com.**")
// search the com.company package for @Component classes
public class SmartCompoundApplication {    
 
	public static void main(String[] args) {
		SpringApplication.run(SmartCompoundApplication.class, args);
	}
}

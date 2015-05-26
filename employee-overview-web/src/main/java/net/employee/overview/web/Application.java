package net.employee.overview.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

@Configuration
@ImportResource("classpath*:emp-service-context.xml")
class ServiceContextConfiguration {

}

@Configuration
@ImportResource("classpath*:emp-dao-context.xml")
class DaoContextConfiguration {

}

package net.employee.overview.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "net.employee.overview.dao")
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

@Configuration
@ImportResource("classpath*:emp-dao-context.xml")
class DaoContextConfiguration {

}

@Configuration
@ImportResource("classpath*:emp-service-context.xml")
class ServiceContextConfiguration {

}


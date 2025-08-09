package dev.rennen.springdatamongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringDataMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataMongoApplication.class, args);
    }

}

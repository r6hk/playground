package dev.rennen.springbootproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootProjectApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringbootProjectApplication.class, args);
    }

}

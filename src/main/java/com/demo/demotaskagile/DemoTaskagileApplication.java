package com.demo.demotaskagile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// docker run -d --name rabbitmq -p 5672:5672 -p 30000:15672 --restart=unless-stopped rabbitmq:management
@SpringBootApplication
public class DemoTaskagileApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoTaskagileApplication.class, args);
    }

}

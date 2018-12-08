package io.wsd.busenforcer.centerapp;

import io.wsd.busenforcer.agents.commandcenter.integration.spring.EnableCommandCenterAgent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCommandCenterAgent
public class CenterAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CenterAppApplication.class, args);
    }
}

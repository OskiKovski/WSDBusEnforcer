package io.wsd.busenforcer.policeapp;

import io.wsd.busenforcer.agents.bus.integration.spring.EnableBusAgent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
@EnableBusAgent
public class PoliceAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoliceAppApplication.class, args);
    }

}
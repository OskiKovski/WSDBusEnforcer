package io.wsd.busenforcer.policeapp;

import io.wsd.busenforcer.agents.police.integration.spring.EnablePoliceAgent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
@EnablePoliceAgent
public class PoliceAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoliceAppApplication.class, args);
    }

}
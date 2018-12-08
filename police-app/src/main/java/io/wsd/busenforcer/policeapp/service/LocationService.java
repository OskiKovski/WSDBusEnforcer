package io.wsd.busenforcer.policeapp.service;

import io.wsd.busenforcer.agents.bus.model.BusState;
import io.wsd.busenforcer.policeapp.client.ApiORSClient;
import io.wsd.busenforcer.policeapp.client.dto.SummaryDTO;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class LocationService {

    private static final String PROFILE = "driving-car";
    private static final String COORDINATES = "21.042955,52.273268|21.048192,52.270025";

    private Logger logger = LoggerFactory.getLogger(LocationService.class);
    private final ApiORSConfiguration apiConfig;

    @Autowired
    private ApiORSClient apiORSClient;

    @Autowired
    private AgentService agentService;

    public LocationService(ApiORSConfiguration apiConfig) {
        this.apiConfig = apiConfig;
    }

    public void updateLocation() {
        BusState busState = agentService.getBusState();
        Optional<SummaryDTO> location = apiORSClient.getDistance(apiConfig.apiKey, PROFILE, COORDINATES);
        location.ifPresent(l -> logger.info(l.toString()));
//        String status = location.map(l -> agentService.updateLocation(l.getLat(), l.getLon())).orElse("Fail!");
//        logger.info("Location update: " + status);
    }


    @Component
    @Getter
    @Setter
    @ConfigurationProperties(prefix = "api.ors")
    private static class ApiORSConfiguration {

        @NotNull
        private String url;
        @NotNull
        private String resource_id;
        @NotNull
        private String apiKey;
    }
}

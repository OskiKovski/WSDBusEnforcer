package io.wsd.busenforcer.policeapp.service;

import io.wsd.busenforcer.agents.common.model.Location;
import io.wsd.busenforcer.agents.police.model.PoliceState;
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

    private Logger logger = LoggerFactory.getLogger(LocationService.class);
    private final ApiORSConfiguration apiConfig;

    @Autowired
    private ApiORSClient apiORSClient;

    @Autowired
    private AgentService agentService;

    public LocationService(ApiORSConfiguration apiConfig) {
        this.apiConfig = apiConfig;
    }

    public SummaryDTO getDistance(Double busLatitude, Double busLongitude) {
        PoliceState policeState = agentService.getPoliceState();
        String concatenatedCoordinates = concatCoordinates(policeState.getLocation(), busLatitude, busLongitude);
        Optional<SummaryDTO> location = apiORSClient.getDistance(apiConfig.apiKey, PROFILE, concatenatedCoordinates);
        location.ifPresent(l -> logger.info(l.toString()));
        return location.orElse(null);
    }

    private String concatCoordinates(Location policeLocation, Double busLatitude, Double busLongitude){
        return String.valueOf(policeLocation.getLon()) +
                "," +
                policeLocation.getLat() +
                "|" +
                busLongitude +
                "," +
                busLatitude;
    }

    @Component
    @Getter
    @Setter
    @ConfigurationProperties(prefix = "api.ors")
    private static class ApiORSConfiguration {

        @NotNull
        private String url;
        @NotNull
        private String apiKey;
    }
}

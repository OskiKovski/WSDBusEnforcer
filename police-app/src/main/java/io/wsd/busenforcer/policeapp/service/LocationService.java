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

    private Logger logger = LoggerFactory.getLogger(LocationService.class);
    private final ApiORSConfiguration apiConfig;

    @Autowired
    private ApiORSClient apiORSClient;

    @Autowired
    private AgentService agentService;

    public LocationService(ApiORSConfiguration apiConfig) {
        this.apiConfig = apiConfig;
    }

    public SummaryDTO getDistance(Double lat1, Double long1, Double lat2, Double long2) {
        String concatedCoordinates = concatCoordinates(lat1, long1, lat2, long2);
        Optional<SummaryDTO> location = apiORSClient.getDistance(apiConfig.apiKey, PROFILE, concatedCoordinates);
        location.ifPresent(l -> logger.info(l.toString()));
        return location.orElse(null);
    }

    private String concatCoordinates(Double lat1, Double long1, Double lat2, Double long2){
        return new StringBuilder()
                .append(long1)
                .append(",")
                .append(lat1)
                .append("|")
                .append(long2)
                .append(",")
                .append(lat2)
                .toString();
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

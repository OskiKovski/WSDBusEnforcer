package io.wsd.busenforcer.busapp.service;

import io.wsd.busenforcer.agents.bus.model.BusState;
import io.wsd.busenforcer.busapp.client.ApiUMClient;
import io.wsd.busenforcer.busapp.client.dto.LocationInfoDTO;
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

    private static final String BUS_TYPE = "1";

    private Logger logger = LoggerFactory.getLogger(LocationService.class);
    private final ApiUMConfiguration apiConfig;

    @Autowired
    private ApiUMClient apiUmClient;

    @Autowired
    private AgentService agentService;

    public LocationService(ApiUMConfiguration apiConfig) {
        this.apiConfig = apiConfig;
    }

    public void updateLocation() {
        logger.info("Updating location via UM api.");
        BusState busState = agentService.getBusState();
        Optional<LocationInfoDTO> location = apiUmClient.getLocation(apiConfig.resource_id, apiConfig.apiKey,
                BUS_TYPE, busState.getLine(), busState.getBrigade());
        location.ifPresent(l -> logger.info(l.toString()));
        String status = location.map(l -> agentService.updateLocation(l.getLat(), l.getLon())).orElse("Fail!");
        logger.info("Location update: " + status);
    }


    @Component
    @Getter
    @Setter
    @ConfigurationProperties(prefix = "api.um")
    private static class ApiUMConfiguration {

        @NotNull
        private String url;
        @NotNull
        private String resource_id;
        @NotNull
        private String apiKey;
    }
}

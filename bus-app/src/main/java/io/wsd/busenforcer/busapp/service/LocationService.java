package io.wsd.busenforcer.busapp.service;

import io.wsd.busenforcer.busapp.client.ApiUMClient;
import io.wsd.busenforcer.busapp.client.dto.LocationInfo;
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
    private final BusConfig busConfig;
    private final ApiUMConfiguration apiConfig;

    @Autowired
    private ApiUMClient apiUmClient;

    @Autowired
    private AgentRunnerService agentRunnerService;

    public LocationService(ApiUMConfiguration apiConfig, BusConfig busConfig) {
        this.apiConfig = apiConfig;
        this.busConfig = busConfig;
    }

    public void updateLocation() {
        logger.info("Updating location.");
        Optional<LocationInfo> location = apiUmClient.getLocation(apiConfig.resource_id, apiConfig.apiKey,
                BUS_TYPE, busConfig.line, busConfig.brigade);
        if (location.isPresent()) {
            agentRunnerService.updateLocation(location.get().getLat(), location.get().getLon());
            logger.info("Location updated: " + location.get() + ".");
        } else {
            logger.warn("Failed to update location.");
        }
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

    @Component
    @Getter
    @Setter
    @ConfigurationProperties(prefix = "bus")
    private static class BusConfig {

        @NotNull
        private String line;
        @NotNull
        private String brigade;
    }
}

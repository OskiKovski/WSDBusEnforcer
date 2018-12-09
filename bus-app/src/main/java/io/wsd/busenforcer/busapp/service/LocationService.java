package io.wsd.busenforcer.busapp.service;

import io.wsd.busenforcer.agents.bus.model.BusState;
import io.wsd.busenforcer.busapp.client.ApiUMClient;
import io.wsd.busenforcer.busapp.client.dto.LocationInfoDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;


@Slf4j
@Service
public class LocationService {

    private static final String BUS_TYPE = "1";

    private final ApiUMConfiguration apiConfig;

    @Autowired
    private ApiUMClient apiUmClient;

    @Autowired
    private AgentService agentService;

    public LocationService(ApiUMConfiguration apiConfig) {
        this.apiConfig = apiConfig;
    }

    public void updateLocation() {
        BusState busState = agentService.getBusState();
        Optional<LocationInfoDTO> location = apiUmClient.getLocation(apiConfig.resource_id, apiConfig.apiKey,
                BUS_TYPE, busState.getLine(), busState.getBrigade());
        String status = location.map(l -> agentService.updateLocation(l.getLat(), l.getLon())).orElse("Fail!");
        log.info("Location update via UM api:" + status);
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

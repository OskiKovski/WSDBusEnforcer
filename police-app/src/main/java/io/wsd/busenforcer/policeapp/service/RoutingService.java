package io.wsd.busenforcer.policeapp.service;

import io.wsd.busenforcer.agents.common.model.Location;
import io.wsd.busenforcer.policeapp.client.ApiORSClient;
import io.wsd.busenforcer.policeapp.client.dto.SummaryDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

import static io.wsd.busenforcer.policeapp.client.ApiORSClient.DRIVING_CAR_PROFILE;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoutingService {

    private final ApiORSConfiguration apiConfig;

    private final ApiORSClient apiORSClient;

    public SummaryDTO getRoutingInfo(Location start, Location end) {
        String coordinates = concatCoordinates(start, end);
        Optional<SummaryDTO> routeSummary = apiORSClient.getDistance(apiConfig.apiKey, DRIVING_CAR_PROFILE,
                coordinates);
        routeSummary.ifPresent(s -> log.info(s.toString()));
        return routeSummary.orElse(null);
    }

    private String concatCoordinates(Location start, Location end) {
        return new StringBuilder()
                .append(start.getLon())
                .append(",")
                .append(start.getLat())
                .append("|")
                .append(end.getLon())
                .append(",")
                .append(end.getLat())
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
        private String apiKey;
    }
}

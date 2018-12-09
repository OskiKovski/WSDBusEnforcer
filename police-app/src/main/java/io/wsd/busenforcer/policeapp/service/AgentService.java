package io.wsd.busenforcer.policeapp.service;

import io.wsd.busenforcer.agents.common.model.Location;
import io.wsd.busenforcer.agents.police.integration.spring.PoliceAgentRunner;
import io.wsd.busenforcer.agents.police.model.PoliceState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class AgentService {

    private final PoliceAgentRunner policeAgentRunner;

    private final RoutingService routingService;

    @EventListener(ApplicationReadyEvent.class)
    public void startAgent() {
        try {
            policeAgentRunner.runAgent();
            policeAgentRunner.registerInterventionEvaluator(location -> {
                Location start = policeAgentRunner.viewState().getLocation();
                return 1 / routingService.getRoutingInfo(start, location).getDuration();
            });
        } catch (Exception e) {
            log.error("Failed to run agent.", e);
        }
    }

    public PoliceState getPoliceState() {
        return policeAgentRunner.viewState();
    }

    public void updateLocation(@NotNull Double lat, @NotNull Double lon) {
        policeAgentRunner.updateLocation(Location.of(lat, lon));
    }
}

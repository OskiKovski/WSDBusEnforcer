package io.wsd.busenforcer.centerapp.service;

import io.wsd.busenforcer.agents.bus.model.BusState;
import io.wsd.busenforcer.agents.commandcenter.model.CommandCenterState;
import io.wsd.busenforcer.centerapp.agent.CommandCenterAgentRunner;
import io.wsd.busenforcer.centerapp.api.rest.dto.BusDTO;
import io.wsd.busenforcer.centerapp.api.rest.dto.ListsResultDTO;
import io.wsd.busenforcer.centerapp.api.rest.dto.LocationDTO;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AgentService {

    private final CommandCenterAgentRunner commandCenterAgentRunner;

    @EventListener(ApplicationReadyEvent.class)
    public void startAgent() {
        commandCenterAgentRunner.runAgent();
    }

    public ListsResultDTO getLists() {
        CommandCenterState commandCenterState = commandCenterAgentRunner.viewState();
        List<BusDTO> buses = commandCenterState.getBusStates().values().stream()
                .sorted(Comparator.comparing(BusState::getLine))
                .map(this::mapBusStateToBusDTO)
                .collect(Collectors.toList());
        return new ListsResultDTO(buses, Collections.emptyList());
    }

    private BusDTO mapBusStateToBusDTO(BusState s) {
        return new BusDTO(getId(s), getName(s), getPosition(s));
    }

    private String getId(BusState s) {
        return s.getLine() + "." + s.getBrigade();
    }

    private String getName(BusState s) {
        return "Autobus " + s.getLine() + " Brygada " + s.getBrigade();
    }

    private LocationDTO getPosition(BusState s) {
        return new LocationDTO(s.getLocation().getLat(), s.getLocation().getLon());
    }
}

package io.wsd.busenforcer.centerapp.service;

import io.wsd.busenforcer.agents.bus.model.BusState;
import io.wsd.busenforcer.agents.commandcenter.integration.spring.CommandCenterAgentRunner;
import io.wsd.busenforcer.agents.common.model.Location;
import io.wsd.busenforcer.agents.police.model.PoliceState;
import io.wsd.busenforcer.centerapp.api.rest.dto.BusDTO;
import io.wsd.busenforcer.centerapp.api.rest.dto.ListsResultDTO;
import io.wsd.busenforcer.centerapp.api.rest.dto.LocationDTO;
import io.wsd.busenforcer.centerapp.api.rest.dto.PoliceUnitDTO;
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
        return commandCenterAgentRunner.viewState()
                .map(state -> {
                    List<BusDTO> buses = state.getBusStates().values().stream()
                            .sorted(Comparator.comparing(BusState::getLine, Comparator.nullsLast(Comparator.naturalOrder())))
                            .map(this::mapBusStateToBusDTO)
                            .collect(Collectors.toList());
                    List<PoliceUnitDTO> policeUnits = state.getPoliceStates().values().stream()
                            .sorted(Comparator.comparing(PoliceState::getId, Comparator.nullsLast(Comparator.naturalOrder())))
                            .map(this::mapPoliceStateToPoliceUnitDTO)
                            .collect(Collectors.toList());
                    return new ListsResultDTO(buses, policeUnits);
                })
                .orElse(new ListsResultDTO(Collections.emptyList(), Collections.emptyList()));
    }

    private PoliceUnitDTO mapPoliceStateToPoliceUnitDTO(PoliceState s) {
        String id = s.getId();
        String name = "Jednostka " + s.getId();
        LocationDTO position = getPosition(s.getLocation());
        return new PoliceUnitDTO(id, name, position);
    }

    private BusDTO mapBusStateToBusDTO(BusState s) {
        String id = s.getLine() + "." + s.getBrigade();
        String name = "Autobus " + s.getLine() + " Brygada " + s.getBrigade();
        LocationDTO position = getPosition(s.getLocation());
        return new BusDTO(id, name, position);
    }

    private LocationDTO getPosition(Location l) {
        return new LocationDTO(l.getLat(), l.getLon());
    }
}

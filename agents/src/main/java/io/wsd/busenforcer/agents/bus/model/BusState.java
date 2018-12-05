package io.wsd.busenforcer.agents.bus.model;

import io.wsd.busenforcer.agents.common.model.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@Builder(toBuilder = true)
@Data
public class BusState implements Cloneable {
    String line;
    String brigade;
    Location location;
}

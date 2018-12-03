package io.wsd.busenforcer.agents.bus.o2a;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class UpdateLocationO2A {
    private Double lat;
    private Double lon;
}

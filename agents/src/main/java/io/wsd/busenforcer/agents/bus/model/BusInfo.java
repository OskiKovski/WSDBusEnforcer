package io.wsd.busenforcer.agents.bus.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BusInfo {
    String line;
    String brigade;
}

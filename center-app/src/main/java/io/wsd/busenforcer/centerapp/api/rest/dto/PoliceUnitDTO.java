package io.wsd.busenforcer.centerapp.api.rest.dto;

import lombok.Data;

@Data
public class PoliceUnitDTO {
    private final String id;
    private final String name;
    private final LocationDTO position;
}

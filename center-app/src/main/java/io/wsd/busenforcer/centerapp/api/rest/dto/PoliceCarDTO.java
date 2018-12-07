package io.wsd.busenforcer.centerapp.api.rest.dto;

import lombok.Data;

@Data
public class PoliceCarDTO {
    private final String id;
    private final String name;
    // TODO: 07.12.2018 jkumor - to be consistent let's use location everywhere?
    private final LocationDTO position;
}

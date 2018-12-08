package io.wsd.busenforcer.centerapp.api.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListsResultDTO {
    private final List<BusDTO> buses;
    private final List<PoliceUnitDTO> policeUnits;
}

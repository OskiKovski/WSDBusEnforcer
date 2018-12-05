package io.wsd.busenforcer.busapp.api.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BusInfoDTO {
    String line;
    String brigade;
}

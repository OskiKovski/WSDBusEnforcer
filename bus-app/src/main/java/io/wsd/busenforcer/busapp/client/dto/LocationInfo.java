package io.wsd.busenforcer.busapp.client.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class LocationInfo {
    private Double lat;
    private Double lon;
    private Date time;
    private String lines;
    private String brigade;
}

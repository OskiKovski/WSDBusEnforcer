package io.wsd.busenforcer.busapp.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationInfo {
    private Double lat;
    private Double lon;
    private Date date;
    private String line;
    private String brigade;
}

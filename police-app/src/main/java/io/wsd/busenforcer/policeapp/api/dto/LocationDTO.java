package io.wsd.busenforcer.policeapp.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class LocationDTO {
    private Double lat;
    private Double lon;
}

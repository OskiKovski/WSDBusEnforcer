package io.wsd.busenforcer.agents.common.model;

import com.javadocmd.simplelatlng.LatLng;
import lombok.Data;

import java.io.Serializable;

@Data
public class Location implements Serializable {
    final Double lat;
    final Double lon;

    public LatLng toLatLng() {
        return new LatLng(lat, lon);
    }
}

package io.wsd.busenforcer.agents.common.model;

import com.javadocmd.simplelatlng.LatLng;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Value()
@AllArgsConstructor(staticName = "of")
public class Location implements Serializable {
    Double lat;
    Double lon;

    private Location() {
        lat = 0.0;
        lon = 0.0;
    }

    public static Location zero() {
        return new Location();
    }

    public LatLng toLatLng() {
        return new LatLng(lat, lon);
    }
}

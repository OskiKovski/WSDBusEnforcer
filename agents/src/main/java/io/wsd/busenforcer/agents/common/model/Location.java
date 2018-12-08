package io.wsd.busenforcer.agents.common.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Location implements Serializable {
    final Double lat;
    final Double lon;
}

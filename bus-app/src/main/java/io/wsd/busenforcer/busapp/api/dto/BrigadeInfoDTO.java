package io.wsd.busenforcer.busapp.api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class BrigadeInfoDTO {
    private String line;
    private String brigade;
}

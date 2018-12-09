package io.wsd.busenforcer.policeapp.api.rest;

import io.wsd.busenforcer.policeapp.client.dto.SummaryDTO;
import io.wsd.busenforcer.policeapp.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/police/api")
public class PoliceSimulationApiController {

    @Autowired
    LocationService locationService;

    @RequestMapping(value = "/getDistance", method = RequestMethod.GET)
    public SummaryDTO getDistance(@RequestParam ("busLatitude") Double busLatitude,
                                  @RequestParam ("busLongitude") Double busLongitude) {
        return locationService.getDistance(busLatitude, busLongitude);
    }
}

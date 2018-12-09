package io.wsd.busenforcer.policeapp.api.rest;

import io.wsd.busenforcer.policeapp.api.rest.dto.BrigadeInfoDTO;
import io.wsd.busenforcer.policeapp.client.dto.SummaryDTO;
import io.wsd.busenforcer.policeapp.service.AgentService;
import io.wsd.busenforcer.policeapp.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/police/api")
public class BusSimulationApiController {

    @Autowired
    AgentService agentService;

    @Autowired
    LocationService locationService;

    @RequestMapping(value = "/getDistance", method = RequestMethod.GET)
    public SummaryDTO getDistance(@RequestParam ("lat1") Double lat1,
                                  @RequestParam ("long1") Double long1,
                                  @RequestParam ("lat2") Double lat2,
                                  @RequestParam ("long2") Double long2) {
        return locationService.getDistance(lat1, long1, lat2, long2);
    }
}

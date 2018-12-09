package io.wsd.busenforcer.policeapp.api.rest;

import io.wsd.busenforcer.agents.common.model.Location;
import io.wsd.busenforcer.policeapp.client.dto.SummaryDTO;
import io.wsd.busenforcer.policeapp.service.AgentService;
import io.wsd.busenforcer.policeapp.service.RoutingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/police/api")
public class PoliceApiController {

    @Autowired
    RoutingService routingService;

    @RequestMapping(value = "/getRouting", method = RequestMethod.GET)
    public SummaryDTO getRouting(@RequestParam ("lat1") Double lat1,
                                  @RequestParam ("long1") Double lon1,
                                  @RequestParam ("lat2") Double lat2,
                                  @RequestParam ("long2") Double lon2) {
        return routingService.getRoutingInfo(Location.of(lat1, lon1), Location.of(lat2, lon2));
    }
}

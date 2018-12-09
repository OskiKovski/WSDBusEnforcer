package io.wsd.busenforcer.policeapp.api.rest;

import io.wsd.busenforcer.policeapp.service.RoutingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/police/api")
public class PoliceSimulationApiController {

    @Autowired
    RoutingService routingService;

//    @RequestMapping(value = "/getDistance", method = RequestMethod.GET)
//    public SummaryDTO getDistance(@RequestParam ("busLatitude") Double busLatitude,
//                                  @RequestParam ("busLongitude") Double busLongitude) {
//        return routingService.getDistance(busLatitude, busLongitude);
//    }
}

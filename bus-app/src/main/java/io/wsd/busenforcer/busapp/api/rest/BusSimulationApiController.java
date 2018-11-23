package io.wsd.busenforcer.busapp.api.rest;

import io.wsd.busenforcer.busapp.service.BusSimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/bus/api")
public class BusSimulationApiController {

    @Autowired
    BusSimulationService busSimulationService;

    @RequestMapping(value = "/raiseEvent", method = RequestMethod.POST)
    public String raiseEvent() {
        return busSimulationService.raiseEvent();
    }
}

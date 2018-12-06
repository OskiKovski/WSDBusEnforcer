package io.wsd.busenforcer.centerapp.api.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/center/api")
public class CommandApiController {

    @CrossOrigin(origins = "${cors.frontend.url}")
    @GetMapping(value = "/getLists", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getLists() {
        return "{ \"buses\":[ { \"id\":\"523\", \"name\":\"Autobus523\", \"position\":[ 52.253354, 20.996054 ] }, { \"id\":\"162\", \"name\":\"Autobus162\", \"position\":[ 52.252106, 20.998135 ] }, { \"id\":\"20\", \"name\":\"Tramwaj20\", \"position\":[ 52.253866, 21.000045 ] } ], \"policeCars\":[ { \"id\":\"007\", \"name\":\"007\", \"position\":[ 52.255094, 20.997438 ] }, { \"id\":\"W11\", \"name\":\"W11\", \"position\":[ 52.254923, 20.995979 ] } ] }";
    }
}

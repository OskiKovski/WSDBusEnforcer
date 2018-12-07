package io.wsd.busenforcer.centerapp.api.rest;

import io.wsd.busenforcer.centerapp.api.rest.dto.ListsResultDTO;
import io.wsd.busenforcer.centerapp.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/center/api")
@CrossOrigin(origins = "${cors.frontend.url}")
public class CommandApiController {

    @Autowired
    private AgentService agentService;

    @GetMapping(value = "/getLists", produces = MediaType.APPLICATION_JSON_VALUE)
    public ListsResultDTO getLists() {
        return agentService.getLists();
    }
}

package io.wsd.busenforcer.busapp.client;

import io.wsd.busenforcer.busapp.client.configuration.ApiUMConfiguration;
import io.wsd.busenforcer.busapp.client.dto.LocationInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name = "api-um", url = "${api.um.url}", configuration = ApiUMConfiguration.class)
public interface ApiUMClient {

    @RequestMapping(method = RequestMethod.GET, value = "/action/busestrams_get/")
    Optional<LocationInfo> getLocation(@RequestParam("resource_id") String resourceId,
                                       @RequestParam("apikey") String apiKey,
                                       @RequestParam("type") String type,
                                       @RequestParam("line") String line,
                                       @RequestParam("brigade") String brigade);

}

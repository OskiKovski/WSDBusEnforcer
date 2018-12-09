package io.wsd.busenforcer.policeapp.client;

import io.wsd.busenforcer.policeapp.client.configuration.ApiORSConfiguration;
import io.wsd.busenforcer.policeapp.client.dto.SummaryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name = "api-ors", url = "${api.ors.url}", configuration = ApiORSConfiguration.class)
public interface ApiORSClient {

    public static final String DRIVING_CAR_PROFILE = "driving-car";

    @RequestMapping(method = RequestMethod.GET, value = "/directions")
    Optional<SummaryDTO> getDistance(@RequestParam("api_key") String apiKey,
                                     @RequestParam("profile") String profile,
                                     @RequestParam("coordinates") String coordinates);

}

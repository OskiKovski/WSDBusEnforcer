package io.wsd.busenforcer.busapp.client;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import feign.Feign;
import feign.Param;
import feign.RequestLine;
import feign.gson.GsonDecoder;
import io.wsd.busenforcer.busapp.model.LocationInfo;
import lombok.Getter;
import lombok.Setter;

@Service
public class ApiUMClient {
    
    private final ApiUM apiUM;
    
    public ApiUMClient(ApiUMConfiguration config) {
        this.apiUM = Feign.builder()
                .decoder(new GsonDecoder())
                .target(ApiUM.class, config.url);
    }
    
    public LocationInfo getBusLocation() {
        return null;
    }
    
    private interface ApiUM {
        @RequestLine("GET /action/busestrams_get/?resource_id={resource_id}&apikey={apikey}&type=1&line={line}&brigade={brigade}")
        LocationInfo getBusLocation(@Param("resource_id") String resourceId, @Param("apikey") String apiKey, @Param("line") String line, @Param("brigade") String brigade);
    }

    @Component
    @Getter
    @Setter
    @ConfigurationProperties(prefix = "api.um")
    private static class ApiUMConfiguration {
        
        @NotNull
        private String url;
    }
}

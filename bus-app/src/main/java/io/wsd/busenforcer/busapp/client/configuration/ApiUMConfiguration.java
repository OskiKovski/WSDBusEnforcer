package io.wsd.busenforcer.busapp.client.configuration;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import io.wsd.busenforcer.busapp.client.dto.LocationInfo;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ApiUMConfiguration {

    @Bean
    public Decoder decoder() {
        return new Decoder() {
            @Override
            public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
                JsonObject jsonObject = new Gson().fromJson(response.body().asReader(), JsonObject.class);
                Type listType = new TypeToken<ArrayList<LocationInfo>>() {
                }.getType();
                // @formatter:off
                List<LocationInfo> resultList = new GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                        .create()
                        .fromJson(jsonObject.get("result"), listType);
                // @formatter:on
                return resultList.stream().findFirst();
            }
        };
    }
}

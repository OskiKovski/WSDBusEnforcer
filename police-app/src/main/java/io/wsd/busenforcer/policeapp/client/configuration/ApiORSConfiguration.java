package io.wsd.busenforcer.policeapp.client.configuration;

import com.google.gson.*;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import io.wsd.busenforcer.policeapp.client.dto.SummaryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Optional;

public class ApiORSConfiguration {

    @Bean
    public Decoder decoder() {
        return new Decoder() {
            @Override
            public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
                JsonObject jsonObject = new Gson().fromJson(response.body().asReader(), JsonObject.class);

                JsonElement jsonElement = jsonObject
                        .getAsJsonArray("routes")
                        .get(0)
                        .getAsJsonObject()
                        .get("summary");

                SummaryDTO summaryDTO = (new Gson()).fromJson(jsonElement, SummaryDTO.class);
                return Optional.of(summaryDTO);
            }
        };
    }
}

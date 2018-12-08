package io.wsd.busenforcer.agents.bus.integration.spring;


import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(BusAgentConfiguration.class)
public @interface EnableBusAgent {
}

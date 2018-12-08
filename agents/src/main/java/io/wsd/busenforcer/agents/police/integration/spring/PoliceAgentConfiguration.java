package io.wsd.busenforcer.agents.police.integration.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@ComponentScan("io.wsd.busenforcer.agents.police.integration.spring")
class PoliceAgentConfiguration {
}

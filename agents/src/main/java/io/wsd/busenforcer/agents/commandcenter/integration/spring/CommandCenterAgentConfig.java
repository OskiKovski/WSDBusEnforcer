package io.wsd.busenforcer.agents.commandcenter.integration.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

@Configuration
@ComponentScan("io.wsd.busenforcer.agents.commandcenter.integration.spring")
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
class CommandCenterAgentConfig {
}

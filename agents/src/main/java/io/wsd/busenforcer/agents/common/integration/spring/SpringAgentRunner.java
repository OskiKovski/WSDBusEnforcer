package io.wsd.busenforcer.agents.common.integration.spring;

import io.wsd.busenforcer.agents.common.o2a.O2ACommand;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.IOException;

@Component
public abstract class SpringAgentRunner<T extends Agent> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${agent.conf}")
    private String agentConfigurationPath;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    private AgentContainer container;

    private AgentController agentController;

    protected T agent;

    public void runAgent() {
        try {
            container = createAgentContainer();
            agent = createAgent();
            agentController = startAgentInContainer();
        } catch (IOException | StaleProxyException e) {
            logger.error("Agent run failed.", e);
        }
    }

    private AgentController startAgentInContainer() throws StaleProxyException {
        String agentNickname = buildAgentNickname();
        logger.info("Starting agent: " + agent + " as " + agentNickname);
        AgentController agentController = container.acceptNewAgent(agentNickname, agent);
        agentController.start();
        applicationEventPublisher.publishEvent(new AgentStartedEvent(this));
        return agentController;
    }

    protected abstract String buildAgentNickname();

    private AgentContainer createAgentContainer() throws IOException {
        logger.info("Initializing JADE agent container using configuration: " + agentConfigurationPath);
        Resource resource = new ClassPathResource(agentConfigurationPath);
        Profile profile = new ProfileImpl(Properties.toLeapProperties(PropertiesLoaderUtils.loadProperties(resource)));
        return Runtime.instance().createAgentContainer(profile);
    }

    public boolean sendO2ACommand(@NotNull O2ACommand command) {
        try {
            agentController.putO2AObject(command, AgentController.ASYNC);
            return true;
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
        return false;
    }

    @NotNull
    protected abstract T createAgent();
}

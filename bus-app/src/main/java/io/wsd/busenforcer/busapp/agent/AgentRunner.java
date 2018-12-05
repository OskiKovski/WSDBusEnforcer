package io.wsd.busenforcer.busapp.agent;

import io.wsd.busenforcer.agents.bus.BusAgent;
import io.wsd.busenforcer.agents.bus.model.BusState;
import io.wsd.busenforcer.agents.common.model.Location;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class AgentRunner {

    @Value("${agent.conf}")
    private String agentConfigurationPath;

    @Value("${bus.line}")
    private String line;

    @Value("${bus.brigade}")
    private String brigade;

    private AgentController agentController;

    private BusAgent busAgent;

    @EventListener(ApplicationReadyEvent.class)
    public void runAgent() throws IOException {
        Resource resource = new ClassPathResource(agentConfigurationPath);
        Profile profile = new ProfileImpl(Properties.toLeapProperties(PropertiesLoaderUtils.loadProperties(resource)));
        AgentContainer container = Runtime.instance().createAgentContainer(profile);
        try {
            BusState initialBusState = new BusState(line, brigade, new Location(.0, .0));
            busAgent = new BusAgent(initialBusState);
            agentController = container.acceptNewAgent("explicit-bus-agent", busAgent);
            agentController.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }

    public Optional<AgentController> getAgentController() {
        return Optional.ofNullable(agentController);
    }

    public BusState viewBusState() {
        return busAgent.getBusState().toBuilder().build();
    }
}

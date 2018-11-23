package io.wsd.busenforcer.busapp.agent;

import io.wsd.busenforcer.agents.bus.BusAgent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AgentRunner {

    @Value("${agent.container.host}")
    private String host;

    @Value("${agent.container.port}")
    private int port;

    private AgentController agent;

    @EventListener(ApplicationReadyEvent.class)
    public void runAgent() {
        host = "localhost";
        port = 10099;
        Profile profile = new ProfileImpl(host, port, null, false);
        AgentContainer container = Runtime.instance().createAgentContainer(profile);
        try {
            agent = container
                    .createNewAgent("inprocess-bus-agent", BusAgent.class.getName(), new Object[]{});
            agent.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }

    public Optional<AgentController> getAgent() {
        return Optional.ofNullable(agent);
    }

}

package io.wsd.busenforcer.agents.commandcenter;

import io.wsd.busenforcer.agents.AgentException;
import jade.core.Agent;
import jade.core.NodeFailureMonitor;
import jade.core.ServiceDescriptor;
import jade.core.messaging.TopicManagementService;
import jade.core.nodeMonitoring.NodeMonitoringService;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandCenterAgent extends Agent {
    static final long serialVersionUID = 1L;

    private final Logger logger = LoggerFactory.getLogger(CommandCenterAgent.class);

    @Override
    public void setup() {
        logger.info("CommandCenterAgent started.");
        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType("events-board");
        sd.setName("Command Center Dangerous Events Board");
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException e) {
            logger.error(e.getMessage());
            throw new AgentException(e);
        }
    }

    @Override
    public void takeDown() {
    }
}

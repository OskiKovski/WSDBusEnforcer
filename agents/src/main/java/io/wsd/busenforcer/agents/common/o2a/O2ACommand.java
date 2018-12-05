package io.wsd.busenforcer.agents.common.o2a;

import jade.core.Agent;

public interface O2ACommand<T extends Agent> {
    
    void execute(T agent);
    
    String getName();
}

package io.wsd.busenforcer.agents.bus.behaviours

import jade.core.Agent
import jade.core.behaviours.Behaviour

class ExampleKotlinBehaviour(a: Agent?) : Behaviour(a) {
    override fun action() {
        println("Example behaviour from Kotlin")
    }

    override fun done(): Boolean {
        return true
    }
}
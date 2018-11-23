package io.wsd.busenforcer.agents;

/**
 * Generic agent runtime exception.
 */
public class AgentException extends RuntimeException {
    private final Throwable cause;

    public AgentException(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }
}

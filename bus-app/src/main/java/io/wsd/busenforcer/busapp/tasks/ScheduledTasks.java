package io.wsd.busenforcer.busapp.tasks;

import io.wsd.busenforcer.agents.common.integration.spring.AgentStartedEvent;
import io.wsd.busenforcer.busapp.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Value("${bus.locationRefreshRate}")
    private Long locationRefreshRate;

    @Autowired
    private TaskScheduler scheduler;

    @Autowired
    private LocationService locationService;

    @Async
    @EventListener(AgentStartedEvent.class)
    public void startUpdateLocation() {
        logger.info("Starting periodic location update task.");
        scheduler.schedule(this::updateLocation, new PeriodicTrigger(locationRefreshRate));
    }

    private void updateLocation() {
        locationService.updateLocation();
    }
}

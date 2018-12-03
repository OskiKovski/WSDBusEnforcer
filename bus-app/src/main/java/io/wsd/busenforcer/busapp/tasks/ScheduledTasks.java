package io.wsd.busenforcer.busapp.tasks;

import io.wsd.busenforcer.busapp.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    LocationService locationService;

    @Scheduled(fixedRateString = "${bus.locationRefreshRate}" )
    public void updateLocation() {
        locationService.updateLocation();
    }
}

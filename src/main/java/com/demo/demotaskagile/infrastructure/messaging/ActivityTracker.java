package com.demo.demotaskagile.infrastructure.messaging;

import com.demo.demotaskagile.domain.application.ActivityService;
import com.demo.demotaskagile.domain.common.event.DomainEvent;
import com.demo.demotaskagile.domain.model.activity.Activity;
import com.demo.demotaskagile.domain.model.activity.DomainEventToActivityConverter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ActivityTracker {

    private final static Logger log = LoggerFactory.getLogger(ActivityTracker.class);

    private final ActivityService activityService;
    private final DomainEventToActivityConverter domainEventToActivityConverter;

    @RabbitListener(queues = "#{activityTrackingQueue.name}")
    public void receive(DomainEvent domainEvent) {
        log.debug("Receive domain event: " + domainEvent);

        Activity activity = domainEventToActivityConverter.toActivity(domainEvent);
        // Save the activity only when there is an activity
        // result from the domain event
        if (activity != null) {
            activityService.saveActivity(activity);
        }
    }
}

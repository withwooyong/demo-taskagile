package com.demo.demotaskagile.domain.common.event;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}

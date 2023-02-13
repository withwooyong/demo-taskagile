package com.demo.demotaskagile.domain.common.event;

import com.demo.demotaskagile.domain.model.user.UserId;
import com.demo.demotaskagile.utils.IpAddress;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
public abstract class DomainEvent implements Serializable {

    private static final long serialVersionUID = 8945128060450240352L;

    private UserId userId;
    private IpAddress ipAddress;
    private Date occurredAt;

    public DomainEvent(TriggeredBy triggeredBy) {
        this.userId = triggeredBy.getUserId();
        this.ipAddress = triggeredBy.getIpAddress();
        this.occurredAt = new Date();
    }

    public DomainEvent(UserId userId, TriggeredFrom triggeredFrom) {
        this.userId = userId;
        this.ipAddress = triggeredFrom.getIpAddress();
        this.occurredAt = new Date();
    }
}

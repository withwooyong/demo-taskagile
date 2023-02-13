package com.demo.demotaskagile.domain.application.commands;

import com.demo.demotaskagile.domain.common.event.TriggeredBy;
import com.demo.demotaskagile.domain.model.user.UserId;
import com.demo.demotaskagile.utils.IpAddress;
import io.jsonwebtoken.lang.Assert;

import java.util.Objects;

public abstract class UserCommand implements TriggeredBy {

    private UserId userId;

    private IpAddress ipAddress;

    public void triggeredBy(UserId userId, IpAddress ipAddress) {
        Assert.notNull(userId, "Parameter `userId` must not be null");
        Assert.notNull(ipAddress, "Parameter `ipAddress` must not be null");

        this.userId = userId;
        this.ipAddress = ipAddress;
    }

    @Override
    public UserId getUserId() {
        return userId;
    }

    @Override
    public IpAddress getIpAddress() {
        return ipAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCommand)) return false;
        UserCommand that = (UserCommand) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(ipAddress, that.ipAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, ipAddress);
    }
}

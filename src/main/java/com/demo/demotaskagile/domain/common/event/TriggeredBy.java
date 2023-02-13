package com.demo.demotaskagile.domain.common.event;

import com.demo.demotaskagile.domain.model.user.UserId;
import com.demo.demotaskagile.utils.IpAddress;

public interface TriggeredBy {

    UserId getUserId();

    IpAddress getIpAddress();
}

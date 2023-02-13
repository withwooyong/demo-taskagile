package com.demo.demotaskagile.web.apis;

import com.demo.demotaskagile.domain.application.commands.AnonymousCommand;
import com.demo.demotaskagile.domain.application.commands.UserCommand;
import com.demo.demotaskagile.domain.model.user.SimpleUser;
import com.demo.demotaskagile.utils.RequestUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractBaseController {

    void addTriggeredBy(UserCommand command, HttpServletRequest request) {
        Assert.notNull(request.getUserPrincipal(), "User principal must be present in the request");
        UsernamePasswordAuthenticationToken userPrincipal = (UsernamePasswordAuthenticationToken) request.getUserPrincipal();
        SimpleUser currentUser = (SimpleUser) userPrincipal.getPrincipal();
        command.triggeredBy(currentUser.getUserId(), RequestUtils.getIpAddress(request));
    }

    void addTriggeredBy(AnonymousCommand command, HttpServletRequest request) {
        command.triggeredBy(RequestUtils.getIpAddress(request));
    }
}

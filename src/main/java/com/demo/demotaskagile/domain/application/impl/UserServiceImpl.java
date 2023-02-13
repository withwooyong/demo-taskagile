package com.demo.demotaskagile.domain.application.impl;

import com.demo.demotaskagile.domain.application.UserService;
import com.demo.demotaskagile.domain.application.commands.RegisterCommand;
import com.demo.demotaskagile.domain.common.event.DomainEventPublisher;
import com.demo.demotaskagile.domain.common.mail.MailManager;
import com.demo.demotaskagile.domain.common.mail.MessageVariable;
import com.demo.demotaskagile.domain.model.user.*;
import com.demo.demotaskagile.domain.model.user.events.UserRegisteredEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private RegistrationManagement registrationManagement;
    private DomainEventPublisher domainEventPublisher;
    private MailManager mailManager;
    private UserRepository userRepository;

    public UserServiceImpl(RegistrationManagement registrationManagement,
                           DomainEventPublisher domainEventPublisher,
                           MailManager mailManager,
                           UserRepository userRepository) {
        this.registrationManagement = registrationManagement;
        this.domainEventPublisher = domainEventPublisher;
        this.mailManager = mailManager;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("No user found");
        }
        User user;
        if (username.contains("@")) {
            user = userRepository.findByEmailAddress(username);
        } else {
            user = userRepository.findByUsername(username);
        }
        if (user == null) {
            throw new UsernameNotFoundException("No user found by `" + username + "`");
        }
        return new SimpleUser(user);
    }

    @Override
    public User findById(UserId userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void register(RegisterCommand command) throws RegistrationException {
        Assert.notNull(command, "Parameter `command` must not be null");
        User newUser = registrationManagement.register(
                command.getUsername(),
                command.getEmailAddress(),
                command.getFirstName(),
                command.getLastName(),
                command.getPassword());

        sendWelcomeMessage(newUser);
        domainEventPublisher.publish(new UserRegisteredEvent(newUser, command));
    }

    private void sendWelcomeMessage(User user) {
        mailManager.send(
                user.getEmailAddress(),
                "Welcome to TaskAgile",
                "welcome.ftl",
                MessageVariable.from("user", user)
        );
    }
}

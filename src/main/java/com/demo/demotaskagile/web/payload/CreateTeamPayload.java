package com.demo.demotaskagile.web.payload;

import com.demo.demotaskagile.domain.application.commands.CreateTeamCommand;

public class CreateTeamPayload {

    private String name;

    public CreateTeamCommand toCommand() {
        return new CreateTeamCommand(name);
    }

    public void setName(String name) {
        this.name = name;
    }
}

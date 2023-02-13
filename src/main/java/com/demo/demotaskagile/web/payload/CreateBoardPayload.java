package com.demo.demotaskagile.web.payload;

import com.demo.demotaskagile.domain.application.commands.CreateBoardCommand;
import com.demo.demotaskagile.domain.model.team.TeamId;

public class CreateBoardPayload {

    private String name;
    private String description;
    private long teamId;

    public CreateBoardCommand toCommand() {
        return new CreateBoardCommand(name, description, new TeamId(teamId));
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

}

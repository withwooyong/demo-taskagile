package com.demo.demotaskagile.domain.application.commands;

import com.demo.demotaskagile.domain.model.team.TeamId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateBoardCommand extends UserCommand {

    private final String name;
    private final String description;
    private final TeamId teamId;
}

package com.demo.demotaskagile.domain.application.commands;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateTeamCommand extends UserCommand {

    private final String name;
}

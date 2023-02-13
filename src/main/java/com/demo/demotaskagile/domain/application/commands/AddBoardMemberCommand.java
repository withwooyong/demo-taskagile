package com.demo.demotaskagile.domain.application.commands;


import com.demo.demotaskagile.domain.model.board.BoardId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddBoardMemberCommand extends UserCommand {
    private final BoardId boardId;
    private final String usernameOrEmailAddress;
}

package com.demo.demotaskagile.web.payload;

import com.demo.demotaskagile.domain.application.commands.AddBoardMemberCommand;
import com.demo.demotaskagile.domain.model.board.BoardId;

public class AddBoardMemberPayload {

    private String usernameOrEmailAddress;

    public AddBoardMemberCommand toCommand(BoardId boardId) {
        return new AddBoardMemberCommand(boardId, usernameOrEmailAddress);
    }

    public void setUsernameOrEmailAddress(String usernameOrEmailAddress) {
        this.usernameOrEmailAddress = usernameOrEmailAddress;
    }
}

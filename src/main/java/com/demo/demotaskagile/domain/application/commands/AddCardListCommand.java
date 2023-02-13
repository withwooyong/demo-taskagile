package com.demo.demotaskagile.domain.application.commands;

import com.demo.demotaskagile.domain.model.board.BoardId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddCardListCommand extends UserCommand {

    private final String name;
    private final BoardId boardId;
    private final int position;

    public AddCardListCommand(BoardId boardId, String name, int position) {
        this.boardId = boardId;
        this.name = name;
        this.position = position;
    }
}

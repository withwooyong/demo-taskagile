package com.demo.demotaskagile.domain.application.commands;

import com.demo.demotaskagile.domain.model.board.BoardId;
import com.demo.demotaskagile.domain.model.card.CardPosition;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ChangeCardPositionsCommand extends UserCommand {

    private final BoardId boardId;
    private final List<CardPosition> cardPositions;
}

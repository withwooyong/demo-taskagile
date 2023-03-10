package com.demo.demotaskagile.web.payload;

import com.demo.demotaskagile.domain.application.commands.ChangeCardListPositionsCommand;
import com.demo.demotaskagile.domain.model.board.BoardId;
import com.demo.demotaskagile.domain.model.cardlist.CardListPosition;

import java.util.List;

public class ChangeCardListPositionsPayload {

    private long boardId;
    private List<CardListPosition> cardListPositions;

    public ChangeCardListPositionsCommand toCommand() {
        return new ChangeCardListPositionsCommand(new BoardId(boardId), cardListPositions);
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

    public void setCardListPositions(List<CardListPosition> cardListPositions) {
        this.cardListPositions = cardListPositions;
    }
}

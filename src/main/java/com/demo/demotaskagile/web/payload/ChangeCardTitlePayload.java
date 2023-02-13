package com.demo.demotaskagile.web.payload;

import com.demo.demotaskagile.domain.application.commands.ChangeCardTitleCommand;
import com.demo.demotaskagile.domain.model.card.CardId;

public class ChangeCardTitlePayload {

    private String title;

    public ChangeCardTitleCommand toCommand(long cardId) {
        return new ChangeCardTitleCommand(new CardId(cardId), title);
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

package com.demo.demotaskagile.web.payload;

import com.demo.demotaskagile.domain.application.commands.ChangeCardDescriptionCommand;
import com.demo.demotaskagile.domain.model.card.CardId;

public class ChangeCardDescriptionPayload {

    private String description;

    public ChangeCardDescriptionCommand toCommand(long cardId) {
        return new ChangeCardDescriptionCommand(new CardId(cardId), description);
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

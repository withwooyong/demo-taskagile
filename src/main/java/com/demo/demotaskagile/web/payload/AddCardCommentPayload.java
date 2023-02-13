package com.demo.demotaskagile.web.payload;

import com.demo.demotaskagile.domain.application.commands.AddCardCommentCommand;
import com.demo.demotaskagile.domain.model.card.CardId;

public class AddCardCommentPayload {

    private String comment;

    public AddCardCommentCommand toCommand(CardId cardId) {
        return new AddCardCommentCommand(cardId, comment);
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

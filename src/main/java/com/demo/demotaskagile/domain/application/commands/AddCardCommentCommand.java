package com.demo.demotaskagile.domain.application.commands;


import com.demo.demotaskagile.domain.model.card.CardId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddCardCommentCommand extends UserCommand {

    private final CardId cardId;
    private final String comment;
}

package com.demo.demotaskagile.domain.application.commands;


import com.demo.demotaskagile.domain.model.cardlist.CardListId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddCardCommand extends UserCommand {

    private final CardListId cardListId;
    private final String title;
    private final int position;

}

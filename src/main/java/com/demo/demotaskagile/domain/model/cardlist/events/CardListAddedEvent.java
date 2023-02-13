package com.demo.demotaskagile.domain.model.cardlist.events;

import com.demo.demotaskagile.domain.common.event.TriggeredBy;
import com.demo.demotaskagile.domain.model.board.events.BoardDomainEvent;
import com.demo.demotaskagile.domain.model.cardlist.CardList;
import com.demo.demotaskagile.domain.model.cardlist.CardListId;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CardListAddedEvent extends BoardDomainEvent {

    private static final long serialVersionUID = -877934435476435188L;

    private CardListId cardListId;
    private String cardListName;

    public CardListAddedEvent(CardList cardList, TriggeredBy triggeredBy) {
        super(cardList.getBoardId(), triggeredBy);
        this.cardListId = cardList.getId();
        this.cardListName = cardList.getName();
    }

}

package com.demo.demotaskagile.domain.application.impl;

import com.demo.demotaskagile.domain.application.CardListService;
import com.demo.demotaskagile.domain.application.commands.AddCardListCommand;
import com.demo.demotaskagile.domain.application.commands.ChangeCardListPositionsCommand;
import com.demo.demotaskagile.domain.common.event.DomainEventPublisher;
import com.demo.demotaskagile.domain.model.board.BoardId;
import com.demo.demotaskagile.domain.model.cardlist.CardList;
import com.demo.demotaskagile.domain.model.cardlist.CardListRepository;
import com.demo.demotaskagile.domain.model.cardlist.events.CardListAddedEvent;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CardListServiceImpl implements CardListService {

    private CardListRepository cardListRepository;
    private DomainEventPublisher domainEventPublisher;

    public CardListServiceImpl(CardListRepository cardListRepository,
                               DomainEventPublisher domainEventPublisher) {
        this.cardListRepository = cardListRepository;
        this.domainEventPublisher = domainEventPublisher;
    }

    @Override
    public List<CardList> findByBoardId(BoardId boardId) {
        return cardListRepository.findByBoardId(boardId);
    }

    @Override
    public CardList addCardList(AddCardListCommand command) {
        CardList cardList = CardList.create(command.getBoardId(),
                command.getUserId(), command.getName(), command.getPosition());

        cardListRepository.save(cardList);
        domainEventPublisher.publish(new CardListAddedEvent(cardList, command));
        return cardList;
    }

    @Override
    public void changePositions(ChangeCardListPositionsCommand command) {
        cardListRepository.changePositions(command.getCardListPositions());
    }
}

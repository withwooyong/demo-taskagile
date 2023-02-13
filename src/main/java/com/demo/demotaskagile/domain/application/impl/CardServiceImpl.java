package com.demo.demotaskagile.domain.application.impl;

import com.demo.demotaskagile.domain.application.CardService;
import com.demo.demotaskagile.domain.application.commands.*;
import com.demo.demotaskagile.domain.common.event.DomainEventPublisher;
import com.demo.demotaskagile.domain.model.activity.Activity;
import com.demo.demotaskagile.domain.model.activity.ActivityRepository;
import com.demo.demotaskagile.domain.model.activity.CardActivities;
import com.demo.demotaskagile.domain.model.attachment.Attachment;
import com.demo.demotaskagile.domain.model.attachment.AttachmentManagement;
import com.demo.demotaskagile.domain.model.attachment.AttachmentRepository;
import com.demo.demotaskagile.domain.model.attachment.events.CardAttachmentAddedEvent;
import com.demo.demotaskagile.domain.model.board.BoardId;
import com.demo.demotaskagile.domain.model.card.Card;
import com.demo.demotaskagile.domain.model.card.CardId;
import com.demo.demotaskagile.domain.model.card.CardRepository;
import com.demo.demotaskagile.domain.model.card.events.CardAddedEvent;
import com.demo.demotaskagile.domain.model.card.events.CardDescriptionChangedEvent;
import com.demo.demotaskagile.domain.model.card.events.CardTitleChangedEvent;
import com.demo.demotaskagile.domain.model.cardlist.CardList;
import com.demo.demotaskagile.domain.model.cardlist.CardListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardListRepository cardListRepository;
    private final ActivityRepository activityRepository;
    private final AttachmentManagement attachmentManagement;
    private final AttachmentRepository attachmentRepository;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    public List<Card> findByBoardId(BoardId boardId) {
        return cardRepository.findByBoardId(boardId);
    }

    @Override
    public Card findById(CardId cardId) {
        return cardRepository.findById(cardId);
    }

    @Override
    public List<Activity> findCardActivities(CardId cardId) {
        return activityRepository.findCardActivities(cardId);
    }

    @Override
    public List<Attachment> getAttachments(CardId cardId) {
        return attachmentRepository.findAttachments(cardId);
    }

    @Override
    public Card addCard(AddCardCommand command) {
        CardList cardList = cardListRepository.findById(command.getCardListId());
        Assert.notNull(cardList, "Card list must not be null");

        Card card = Card.create(cardList, command.getUserId(), command.getTitle(), command.getPosition());
        cardRepository.save(card);
        domainEventPublisher.publish(new CardAddedEvent(card, command));
        return card;
    }

    @Override
    public void changePositions(ChangeCardPositionsCommand command) {
        cardRepository.changePositions(command.getCardPositions());
    }

    @Override
    public void changeCardTitle(ChangeCardTitleCommand command) {
        Assert.notNull(command, "Parameter `command` must not be null");

        Card card = findCard(command.getCardId());
        String oldTitle = card.getTitle();
        card.changeTitle(command.getTitle());
        cardRepository.save(card);
        domainEventPublisher.publish(new CardTitleChangedEvent(card, oldTitle, command));
    }

    @Override
    public void changeCardDescription(ChangeCardDescriptionCommand command) {
        Assert.notNull(command, "Parameter `command` must not be null");

        Card card = findCard(command.getCardId());
        String oldDescription = card.getDescription();
        card.changeDescription(command.getDescription());
        cardRepository.save(card);
        domainEventPublisher.publish(new CardDescriptionChangedEvent(card, oldDescription, command));
    }

    @Override
    public Activity addComment(AddCardCommentCommand command) {
        Assert.notNull(command, "Parameter `command` must not be null");

        Card card = findCard(command.getCardId());
        Activity cardActivity = CardActivities.from(
                card, command.getUserId(), command.getComment(), command.getIpAddress());

        activityRepository.save(cardActivity);
        // No need to publish a domain event because the
        return cardActivity;
    }

    @Override
    public Attachment addAttachment(AddCardAttachmentCommand command) {
        Assert.notNull(command, "Parameter `command` must not be null");

        Card card = findCard(command.getCardId());
        Attachment attachment = attachmentManagement.save(
                command.getCardId(), command.getFile(), command.getUserId());

        if (!card.hasCoverImage() && attachment.isThumbnailCreated()) {
            card.addCoverImage(attachment.getFilePath());
            cardRepository.save(card);
        }

        domainEventPublisher.publish(new CardAttachmentAddedEvent(card, attachment, command));
        return attachment;
    }

    private Card findCard(CardId cardId) {
        Card card = cardRepository.findById(cardId);
        Assert.notNull(card, "Card of id " + card + " must exist");
        return card;
    }
}

package com.demo.demotaskagile.web.updater;

import com.demo.demotaskagile.domain.model.board.BoardId;
import com.demo.demotaskagile.domain.model.card.Card;
import com.demo.demotaskagile.utils.JsonUtils;
import com.demo.demotaskagile.web.socket.SubscriptionHub;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CardUpdater {

    /**
     * Update the clients when a card added
     *
     * @param boardId the id of the board the card has been added
     * @param card    the new card
     */
    public void onCardAdded(BoardId boardId, Card card) {
        Map<String, Object> cardData = new HashMap<>();
        cardData.put("id", card.getId().value());
        cardData.put("title", card.getTitle());
        cardData.put("cardListId", card.getCardListId().value());
        cardData.put("position", card.getPosition());

        Map<String, Object> update = new HashMap<>();
        update.put("type", "cardAdded");
        update.put("card", cardData);

        SubscriptionHub.send("/board/" + boardId.value(), JsonUtils.toJson(update));
    }
}

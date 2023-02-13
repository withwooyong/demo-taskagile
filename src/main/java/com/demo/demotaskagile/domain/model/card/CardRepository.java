package com.demo.demotaskagile.domain.model.card;

import com.demo.demotaskagile.domain.model.board.BoardId;

import java.util.List;

public interface CardRepository {

    /**
     * Find a card by its id
     *
     * @param cardId the id of a card
     * @return the card instance or null if not found
     */
    Card findById(CardId cardId);

    /**
     * Find cards of a board
     *
     * @param boardId the id of the board
     * @return a list of cards of that board or an empty list if none found
     */
    List<Card> findByBoardId(BoardId boardId);

    /**
     * Save card
     *
     * @param card the card to save
     */
    void save(Card card);

    /**
     * Change card positions
     *
     * @param cardPositions the card positions
     */
    void changePositions(List<CardPosition> cardPositions);
}

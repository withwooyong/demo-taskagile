package com.demo.demotaskagile.domain.model.board;

import com.demo.demotaskagile.domain.model.user.User;
import com.demo.demotaskagile.domain.model.user.UserId;

import java.util.List;

public interface BoardMemberRepository {

    /**
     * Find members of a board
     *
     * @param boardId the id of the board
     * @return a list of board members
     */
    List<User> findMembers(BoardId boardId);

    /**
     * Save board member
     *
     * @param boardMember the board member to save
     */
    void save(BoardMember boardMember);

    /**
     * Add a user to a board
     *
     * @param boardId the id of the board
     * @param userId  the id of the user
     */
    void add(BoardId boardId, UserId userId);
}

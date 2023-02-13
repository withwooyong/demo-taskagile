package com.demo.demotaskagile.domain.model.board.events;

import com.demo.demotaskagile.domain.common.event.TriggeredBy;
import com.demo.demotaskagile.domain.model.board.BoardId;
import com.demo.demotaskagile.domain.model.user.User;
import com.demo.demotaskagile.domain.model.user.UserId;

public class BoardMemberAddedEvent extends BoardDomainEvent {

    private static final long serialVersionUID = -8979992986207557039L;

    private UserId memberUserId;
    private String memberName;

    public BoardMemberAddedEvent(BoardId boardId, User addedUser, TriggeredBy triggeredBy) {
        super(boardId, triggeredBy);
        this.memberUserId = addedUser.getId();
        this.memberName = addedUser.getFirstName() + " " + addedUser.getLastName();
    }

    public UserId getMemberUserId() {
        return memberUserId;
    }

    public String getMemberName() {
        return memberName;
    }

    @Override
    public String toString() {
        return "BoardMemberAddedEvent{" +
                "boardId=" + getBoardId() +
                ", memberUserId=" + memberUserId +
                ", memberName='" + memberName + '\'' +
                '}';
    }
}

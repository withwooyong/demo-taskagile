package com.demo.demotaskagile.domain.model.team.events;

import com.demo.demotaskagile.domain.common.event.DomainEvent;
import com.demo.demotaskagile.domain.common.event.TriggeredBy;
import com.demo.demotaskagile.domain.model.team.Team;
import com.demo.demotaskagile.domain.model.team.TeamId;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TeamCreatedEvent extends DomainEvent {

    private static final long serialVersionUID = 2714833255396717504L;

    private TeamId teamId;
    private String teamName;

    public TeamCreatedEvent(Team team, TriggeredBy triggeredBy) {
        super(triggeredBy);
        this.teamId = team.getId();
        this.teamName = team.getName();
    }
}

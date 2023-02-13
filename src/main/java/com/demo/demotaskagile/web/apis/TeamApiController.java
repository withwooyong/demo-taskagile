package com.demo.demotaskagile.web.apis;

import com.demo.demotaskagile.domain.application.TeamService;
import com.demo.demotaskagile.domain.application.commands.CreateTeamCommand;
import com.demo.demotaskagile.domain.model.team.Team;
import com.demo.demotaskagile.web.payload.CreateTeamPayload;
import com.demo.demotaskagile.web.results.ApiResult;
import com.demo.demotaskagile.web.results.CreateTeamResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TeamApiController extends AbstractBaseController {

    private TeamService teamService;

    public TeamApiController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/api/teams")
    public ResponseEntity<ApiResult> createTeam(@RequestBody CreateTeamPayload payload,
                                                HttpServletRequest request) {
        CreateTeamCommand command = payload.toCommand();
        addTriggeredBy(command, request);

        Team team = teamService.createTeam(command);
        return CreateTeamResult.build(team);
    }
}

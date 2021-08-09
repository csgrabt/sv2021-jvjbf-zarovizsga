package org.training360.finalexam.teams;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/teams")
@RestController
@AllArgsConstructor
public class TeamController {

    private TeamService teamService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamDTO createTeam(@RequestBody CreateTeamCommand createTeamCommand) {
        return teamService.createTeam(createTeamCommand);
    }
}

package org.training360.finalexam.teams;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.training360.finalexam.player.CreatePlayerCommand;
import org.training360.finalexam.player.PlayerDTO;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping
    public List<TeamDTO> listPlayers() {
        return teamService.listTeams();
    }

    @PostMapping("{id}/players")
    @ResponseStatus(HttpStatus.CREATED)
    public TeamDTO addPlayer(@PathVariable("id") long id, @RequestBody CreatePlayerCommand command) {
        return teamService.addPlayerToTeam(id, command);
    }

    @PutMapping("/api/teams/{id}/players")
    @ResponseStatus(HttpStatus.CREATED)

    public TeamDTO createFinance(@PathVariable("id") long id, @RequestBody  UpdateWithExistingPlayerCommand command) {
        return teamService.createFinance(id, command);
    }

}

package org.training360.finalexam.teams;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.training360.finalexam.player.CreatePlayerCommand;
import org.training360.finalexam.player.PlayerDTO;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequestMapping("/api/teams")
@RestController
@AllArgsConstructor
public class TeamController {

    private TeamService teamService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamDTO createTeam(@Valid @RequestBody CreateTeamCommand createTeamCommand) {
        return teamService.createTeam(createTeamCommand);
    }

    @GetMapping
    public List<TeamDTO> listPlayers() {
        return teamService.listTeams();
    }

    @PostMapping("/{id}/players")
    @ResponseStatus(HttpStatus.CREATED)
    public TeamDTO addPlayer(@PathVariable("id") long id, @RequestBody CreatePlayerCommand command) {
        return teamService.addPlayerToTeam(id, command);
    }


    @PutMapping("/{id}/players")
    public TeamDTO addExistingPlayerToExistingTeam(@PathVariable("id") Long id, @Valid @RequestBody UpdateWithExistingPlayerCommand command) {
        return teamService.addExistingPlayerToExistingTeam(id, command);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(IllegalArgumentException enf) {
        Problem problem =
                Problem.builder()
                        .withType(URI.create("teams/not-found"))
                        .withTitle("Not found")
                        .withStatus(Status.NOT_FOUND)
                        .withDetail(enf.getMessage())
                        .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);

    }
}

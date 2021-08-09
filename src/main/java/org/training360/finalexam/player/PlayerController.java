package org.training360.finalexam.player;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/players")
@RestController
@AllArgsConstructor
public class PlayerController {

    private PlayerService playerService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerDTO createEmployee(@Valid @RequestBody CreatePlayerCommand command) {
        return playerService.createPlayer(command);
    }

    @GetMapping

    public List<PlayerDTO> listPlayers() {
        return playerService.listPlayers();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteElder(@PathVariable("id") Long id) {
        playerService.deletePlayerById(id);
    }
}

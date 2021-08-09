package org.training360.finalexam.player;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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




}

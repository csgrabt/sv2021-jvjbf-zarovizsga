package org.training360.finalexam.player;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlayerService {
    private PlayerRepository repository;
    private ModelMapper modelMapper;

    public PlayerDTO createPlayer(CreatePlayerCommand command) {

        Player player = new Player(
                command.getName(),
                command.getBirthDate(),
                command.getPosition());
        repository.save(player);
        return modelMapper.map(player, PlayerDTO.class);
    }
}

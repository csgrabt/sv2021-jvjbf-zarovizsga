package org.training360.finalexam.player;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<PlayerDTO> listPlayers() {
        return repository
                .findAll()
                .stream()
                .map(n -> modelMapper.map(n, PlayerDTO.class))
                .toList();
    }

    public void deletePlayerById(Long id) {
        repository.delete(repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Cannot delete Player based on id: " + id))
        );
    }
}

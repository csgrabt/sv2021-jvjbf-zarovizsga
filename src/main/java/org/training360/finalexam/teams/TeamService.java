package org.training360.finalexam.teams;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.training360.finalexam.player.CreatePlayerCommand;
import org.training360.finalexam.player.Player;
import org.training360.finalexam.player.PlayerRepository;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.IllegalFormatCodePointException;
import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {
    private ModelMapper modelMapper;
    private TeamRepository repository;
    private PlayerRepository playerRepository;


    public TeamDTO createTeam(CreateTeamCommand createTeamCommand) {
        Team team = new Team(createTeamCommand.getName());
        repository.save(team);
        return modelMapper.map(team, TeamDTO.class);
    }

    public List<TeamDTO> listTeams() {
        return repository
                .findAll()
                .stream()
                .map(n -> modelMapper.map(n, TeamDTO.class))
                .toList();
    }

    @Transactional
    public TeamDTO addPlayerToTeam(long id, CreatePlayerCommand command) {
        Team team = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find team by id"));
        if (command.getBirthDate() == null || command.getPosition() == null) {
            throw new IllegalArgumentException("Position or Birthday cannot be null");
        }

        Player player = new Player(
                command.getName(),
                command.getBirthDate(),
                command.getPosition()
        );
        team.addTeamToPlayer(player);
        return modelMapper.map(team, TeamDTO.class);
    }

    @Transactional
    public TeamDTO addExistingPlayerToExistingTeam(Long id, UpdateWithExistingPlayerCommand command) {
        Player player = playerRepository.findById(command.getPlayerId()).orElseThrow();
        Team team = repository.findById(id).orElseThrow();
        if (player.getTeam() != null) {
            return modelMapper.map(team, TeamDTO.class);
        }


        int counter = 0;

        for (Player item : team.getPlayers()
        ) {
            if (item.getPosition().equals(player.getPosition())) {
                counter++;
            }
        }
        if (counter >= 2) {
            return modelMapper.map(team, TeamDTO.class);
        }

        team.addTeamToPlayer(player);

        return modelMapper.map(team, TeamDTO.class);
    }

}

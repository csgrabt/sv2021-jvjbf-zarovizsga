package org.training360.finalexam.teams;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.training360.finalexam.player.CreatePlayerCommand;
import org.training360.finalexam.player.Player;
import org.training360.finalexam.player.PlayerDTO;
import org.training360.finalexam.player.PlayerRepository;

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
            throw new IllegalArgumentException("Position or Birtday cannot be null");
        }

        Player player = new Player(
                command.getName(),
                command.getBirthDate(),
                command.getPosition()
        );
        team.addTeamToPlayer(player);
        return modelMapper.map(team, TeamDTO.class);
    }
}

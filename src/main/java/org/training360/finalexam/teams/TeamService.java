package org.training360.finalexam.teams;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.training360.finalexam.player.PlayerRepository;

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
}

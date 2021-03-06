package org.training360.finalexam.teams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.training360.finalexam.player.Player;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "team")
    private List<Player> players;

    public Team(String name) {
        this.name = name;
    }



    public void addTeamToPlayer(Player player) {
        if (players == null) {
            players = new ArrayList<>();
        }
        this.players.add(player);
        player.setTeam(this);
    }

}

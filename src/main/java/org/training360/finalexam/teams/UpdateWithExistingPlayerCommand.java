package org.training360.finalexam.teams;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class UpdateWithExistingPlayerCommand {
    private long playerId;


    public UpdateWithExistingPlayerCommand(long playerId) {
        this.playerId = playerId;

    }

}

package org.training360.finalexam.teams;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class UpdateWithExistingPlayerCommand {
    private Long id;


    public UpdateWithExistingPlayerCommand(Long id) {
        this.id = id;
    }
}



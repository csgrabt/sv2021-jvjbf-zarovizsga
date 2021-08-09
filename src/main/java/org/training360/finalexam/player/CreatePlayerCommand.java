package org.training360.finalexam.player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlayerCommand {
    @NotBlank
    private String name;
    private LocalDate birthDate;
    private PositionType position;

    public CreatePlayerCommand(String name) {
        this.name = name;
    }

    public CreatePlayerCommand(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }
}

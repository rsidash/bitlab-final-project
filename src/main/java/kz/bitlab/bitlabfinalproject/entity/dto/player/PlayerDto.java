package kz.bitlab.bitlabfinalproject.entity.dto.player;


import jakarta.validation.constraints.NotEmpty;
import kz.bitlab.bitlabfinalproject.entity.PlayingPosition;
import kz.bitlab.bitlabfinalproject.entity.Team;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamDto;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PlayerDto {
    private String uuid;
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;
    private byte jerseyNumber;
    private String phoneNumber;
    private LocalDate birthdate;
    private PlayingPosition playingPosition;
    private TeamDto team;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFormattedBirthdate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy").withLocale(Locale.ENGLISH);

        return birthdate.format(formatter);
    }
}

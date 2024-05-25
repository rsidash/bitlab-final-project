package kz.bitlab.bitlabfinalproject.entity.dto.player;


import jakarta.validation.constraints.NotEmpty;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamDto;
import kz.bitlab.bitlabfinalproject.enums.PlayingPosition;
import lombok.*;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PlayerDto {
    private String uuid = String.valueOf(UUID.randomUUID());
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;
    private Byte jerseyNumber;
    private String phoneNumber;
    private LocalDate birthdate;
    private PlayingPosition playingPosition;
    private TeamDto team;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Nullable
    public String getFormattedBirthdate() {
        final var formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy").withLocale(Locale.ENGLISH);

        return Objects.nonNull(birthdate) ? birthdate.format(formatter) : null;
    }

    @Nullable
    public Integer getAge() {
        return Objects.nonNull(birthdate) ? Period.between(birthdate, LocalDate.now()).getYears() : null;
    }
}

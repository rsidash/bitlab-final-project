package kz.bitlab.bitlabfinalproject.entity.dto.player;

import kz.bitlab.bitlabfinalproject.enums.PlayingPosition;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PlayerUpdateDto {
    private String firstName;
    private String lastName;
    private byte jerseyNumber;
    private String phoneNumber;
    private LocalDate birthdate;
    private PlayingPosition playingPosition;
}

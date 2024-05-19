package kz.bitlab.bitlabfinalproject.entity.dto.team;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kz.bitlab.bitlabfinalproject.entity.dto.player.PlayerDto;
import kz.bitlab.bitlabfinalproject.entity.security.dto.UserDto;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class TeamDataDto {
    private String uuid;
    @JsonProperty("name")
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("user")
    @NotNull(message = "User cannot be empty")
    private UserDto user;
    @JsonProperty("players")
    private Set<PlayerDto> players;
}
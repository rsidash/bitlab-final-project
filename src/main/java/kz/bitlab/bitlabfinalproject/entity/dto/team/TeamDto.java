package kz.bitlab.bitlabfinalproject.entity.dto.team;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kz.bitlab.bitlabfinalproject.entity.security.dto.UserDto;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {
    private String uuid;
    @JsonProperty("name")
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("user")
    private UserDto user;
}

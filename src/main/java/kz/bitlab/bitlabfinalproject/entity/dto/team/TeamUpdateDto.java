package kz.bitlab.bitlabfinalproject.entity.dto.team;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamUpdateDto {
    @JsonProperty("name")
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @JsonProperty("description")
    private String description;
}

package kz.bitlab.bitlabfinalproject.entity.dto.staff;

import com.fasterxml.jackson.annotation.JsonProperty;
import kz.bitlab.bitlabfinalproject.entity.dto.team.TeamDto;
import kz.bitlab.bitlabfinalproject.enums.JobTitle;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class StaffDto {
    private String uuid;
    @JsonProperty("firstName")
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Byte experience;
    private String description;
    private JobTitle jobTitle;
    private TeamDto team;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}

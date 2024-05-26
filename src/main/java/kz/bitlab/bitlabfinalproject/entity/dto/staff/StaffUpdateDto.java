package kz.bitlab.bitlabfinalproject.entity.dto.staff;

import com.fasterxml.jackson.annotation.JsonProperty;
import kz.bitlab.bitlabfinalproject.enums.JobTitle;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class StaffUpdateDto {
    @JsonProperty("firstName")
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Byte experience;
    private String description;
    private JobTitle jobTitle;
}

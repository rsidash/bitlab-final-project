package kz.bitlab.bitlabfinalproject.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private byte experience;
    private String description;
    private JobTitle jobTitle;
}

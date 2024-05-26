package kz.bitlab.bitlabfinalproject.entity.security.dto;

import kz.bitlab.bitlabfinalproject.entity.security.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDto {
    private String uuid;
    private String firstName;
    private String lastName;
    private List<Role> roles;

    public String getFullName() {
        return lastName + " " + firstName;
    }
}

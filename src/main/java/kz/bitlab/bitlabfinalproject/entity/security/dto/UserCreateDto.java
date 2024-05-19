package kz.bitlab.bitlabfinalproject.entity.security.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserCreateDto {
    private String username;
    private String password;
    private String rePassword;
    private String firstName;
    private String lastName;
}

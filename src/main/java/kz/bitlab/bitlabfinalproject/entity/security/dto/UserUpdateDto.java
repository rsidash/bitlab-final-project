package kz.bitlab.bitlabfinalproject.entity.security.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserUpdateDto {
    private String firstName;
    private String lastName;
}

package kz.bitlab.bitlabfinalproject.entity.security.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserUpdatePasswordDto {
    private String oldPassword;
    private String newPassword;
}

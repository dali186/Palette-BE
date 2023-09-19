package fc.server.palette.member.auth.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginDto {
    @NotNull
    private String email;

    @NotNull
    private String password;
}
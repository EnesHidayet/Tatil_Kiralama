package org.enes.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginRequestDto {
    @Email
    private String email;
    @Size(min = 8, max = 16)
    private String password;
}

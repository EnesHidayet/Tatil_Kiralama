package org.enes.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRegisterRequestDto {
    @Email
    private String email;
    @Size(min = 8, max = 16)
    private String password;
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
    private String phone;
}

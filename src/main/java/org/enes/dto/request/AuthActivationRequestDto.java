package org.enes.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthActivationRequestDto {
    private String token;
    private String activationCode;
}

package org.enes.entity;

import lombok.*;
import org.enes.utility.CodeGenerator;
import org.enes.utility.enums.EStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Auth implements Serializable {
    @Id
    private String id;
    private String email;
    private String password;
    private String phone;
    @Builder.Default
    private String activationCode= CodeGenerator.generateCode();
    @Builder.Default
    private EStatus status= EStatus.PENDING;
}

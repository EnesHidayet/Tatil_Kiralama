package org.enes.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Comments {
    @Id
    private String id;
    private String userId;
    private String hotelId;
    private String comment;
    private Double point;
    @Builder.Default
    private String commentDate= LocalDate.now().toString();
}

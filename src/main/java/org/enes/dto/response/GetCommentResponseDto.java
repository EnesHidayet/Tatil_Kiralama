package org.enes.dto.response;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCommentResponseDto {
    private String userId;
    private String hotelId;
    private String comment;
    private Double point;
    private String commentDate;
}

package org.enes.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageRoomRequestDto {
    private String roomId;
    private String imageUrl;
    private String explanation;
}

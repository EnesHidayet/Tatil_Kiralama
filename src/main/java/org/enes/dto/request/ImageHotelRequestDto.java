package org.enes.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageHotelRequestDto {
    private String hotelId;
    private String imageUrl;
    private String explanation;
}

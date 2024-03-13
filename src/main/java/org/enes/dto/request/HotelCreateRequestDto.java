package org.enes.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelCreateRequestDto {
    private String name;
    private String mainImageUrl;
    private String latitude;
    private String longitude;
}

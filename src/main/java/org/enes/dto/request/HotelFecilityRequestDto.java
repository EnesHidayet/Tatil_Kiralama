package org.enes.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelFecilityRequestDto {
    private String hotelId;
    private String name;
    private String description;
}

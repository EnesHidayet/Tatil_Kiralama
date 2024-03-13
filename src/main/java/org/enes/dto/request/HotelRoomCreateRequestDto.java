package org.enes.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelRoomCreateRequestDto {
    private String hotelId;
    private String name;
    private String description;
    private Double price;
}

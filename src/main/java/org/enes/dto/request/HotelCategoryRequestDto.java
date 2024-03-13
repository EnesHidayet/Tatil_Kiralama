package org.enes.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelCategoryRequestDto {
    private String hotelId;
    private String categoryId;
}

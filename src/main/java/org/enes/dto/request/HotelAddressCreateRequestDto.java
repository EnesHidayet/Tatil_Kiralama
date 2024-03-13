package org.enes.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelAddressCreateRequestDto {
    private String hotelId;
    private String address;
    private String district;
    private String city;
    private String country;
}

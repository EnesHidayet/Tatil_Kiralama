package org.enes.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequestDto {
    private String hotelId;
    private String roomId;
    private String token;
    private String startDate;
    private String endDate;
    private Integer numberOfPeople;
}

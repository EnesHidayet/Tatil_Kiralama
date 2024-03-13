package org.enes.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDto {
    private String reservationId;
    private String paymentMethod;
    private Double paymentAmount;
    private String couponCode;
}

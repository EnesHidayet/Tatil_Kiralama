package org.enes.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Payment {
    @Id
    private String id;
    private String reservationId;
    private String paymentMethod;
    @Builder.Default
    private String paymentDate= LocalDate.now().toString();
    private Double paymentAmount;
    private String couponCode;
    //kupon kullanılddıysa rezervasyondaki total pricedan düşeriz paymentAmount(ödeme tutarı) son hali olur.
}
package org.enes.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Reservation {
    @Id
    private String id;
    private String paymentId;
    private String hotelId;
    private String roomId;
    private String userId;
    private String startDate;
    private String endDate;
    private Integer numberOfPeople;
    private Double totalPrice;
    //rezervasyonda kullanılan kupon kodunu belirtmeli miyiz?
}

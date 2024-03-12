package org.enes.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.enes.utility.enums.EStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class UserProfile implements Serializable {

    @Id
    private String id;
    private String authId;
    private String addressId;
    private String email;
    private String nameSurname;
    private String phone;
    private String passportNo;
    private String passportExpiry;
    private String trIdNo;
    private String couponCode;
    @Builder.Default
    private EStatus status= EStatus.PENDING;

//    private List<Reservation> reservationList;
    //private List<String> favoriteHotels; -> favourite kısmını nasıl tutalım?
    // private List<UserProfile>registeredPerson kısmını nasıl yapalım?



}

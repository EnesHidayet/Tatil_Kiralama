package org.enes.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.enes.utility.enums.EStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
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

    @Builder.Default
    private List<String> reservationListId = new ArrayList<>();
    @Builder.Default
    private List<String> favoriteHotelsId=new ArrayList<>();
    @Builder.Default
    private List<String> commentId=new ArrayList<>();
    @Builder.Default
    private List<UserProfile>registeredPerson=new ArrayList<>();



}

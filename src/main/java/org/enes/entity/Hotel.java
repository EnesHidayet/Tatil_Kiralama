package org.enes.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Hotel {
    @Id
    private String id;
    private String name;
    private String addressId;
    @Builder.Default
    private List<String> imageIdList=new ArrayList<>();
    private String mainImageUrl;
    private String latitude;
    private String longitude;
    @Builder.Default
    private Double point=0.0;
}

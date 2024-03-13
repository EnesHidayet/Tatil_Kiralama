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
public class Room {

    @Id
    private String id;
    private String hotelId;
    private String name;
    private String description;
    private Double price;
    @Builder.Default
    private List<String> imageId=new ArrayList<>();
}

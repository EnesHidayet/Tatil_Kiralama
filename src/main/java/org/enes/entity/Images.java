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
public class Images {

    @Id
    private String id;
    private String imageUrl;
    private String explanation; //bu kısmı enum değil de subcategory mantığıyla hocanınki gibi yapalım  mı
}
package org.enes.repository;

import org.enes.entity.HotelCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HotelCategoryRepository extends MongoRepository<HotelCategory, String> {
    List<HotelCategory> findByCategoryId(String categoryId);
}

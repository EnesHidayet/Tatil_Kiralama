package org.enes.repository;

import org.enes.entity.Comments;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentsRepository extends MongoRepository<Comments, String> {
    List<Comments> findByHotelId(String hotelId);
}

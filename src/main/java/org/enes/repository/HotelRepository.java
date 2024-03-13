package org.enes.repository;

import org.enes.entity.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends MongoRepository<Hotel, String> {
    List<Hotel> findAllByOrderByPointDesc();

}

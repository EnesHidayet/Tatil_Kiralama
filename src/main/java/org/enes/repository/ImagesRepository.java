package org.enes.repository;

import org.enes.entity.Images;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImagesRepository extends MongoRepository<Images, String> {
}

package org.enes.repository;

import org.enes.entity.FacilityFeatures;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FacilityFeaturesRepository extends MongoRepository<FacilityFeatures, String> {
}

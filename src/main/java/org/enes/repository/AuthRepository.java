package org.enes.repository;

import org.enes.entity.Auth;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
public interface AuthRepository extends MongoRepository<Auth, String> {
    Optional<Auth> findByIdAndActivationCode(String id, String code);
    Optional<Auth> findByEmailAndPassword(String email, String password);
    Optional<Auth> findByEmail(String email);
}

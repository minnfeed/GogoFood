package com.example.springbootservices.reponsitory;

import com.example.springbootservices.model.entites.RestaurantProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestaurantProfileRepository extends JpaRepository<RestaurantProfile, UUID> {

    RestaurantProfile findByUserId(UUID userId);

    Optional<RestaurantProfile> findByName(String name);

    boolean existsByUserId(UUID userId);
}
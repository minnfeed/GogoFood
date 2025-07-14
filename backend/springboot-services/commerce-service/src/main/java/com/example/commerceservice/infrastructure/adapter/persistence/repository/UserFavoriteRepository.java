package com.example.commerceservice.infrastructure.adapter.persistence.repository;


import com.example.commerceservice.domain.model.entity.UserFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserFavoriteRepository extends JpaRepository<UserFavorite, UUID> {}
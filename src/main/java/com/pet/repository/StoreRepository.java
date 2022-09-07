package com.pet.repository;

import com.pet.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface StoreRepository extends JpaRepository<StoreEntity, UUID>, JpaSpecificationExecutor<StoreEntity> {
    boolean existsByName(String name);

    StoreEntity findByName(String nameStore);
}

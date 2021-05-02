package com.catalog.importer.repository;

import com.catalog.importer.entity.Campaign;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CampaignRepository extends MongoRepository<Campaign,String> {
    Optional<Campaign> findAllByActive(boolean active);
}

package com.catalog.api.repository;


import com.catalog.api.entity.Campaign;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CampaignRepository extends MongoRepository<Campaign,String> {
    Optional<Campaign> findAllByActive(boolean active);
}

package com.catalog.api.service;

import com.catalog.api.entity.Campaign;

import java.util.List;
import java.util.Optional;

public interface CampaignService {

    Campaign save(Campaign campaign);
    Optional<Campaign> findCampaignById(String id);
    Optional<Campaign> listActiveCampaign();
    List<Campaign> listAllCampaign();
    void deleteCampaign(String id);
}

package com.catalog.importer.service.impl;

import com.catalog.importer.service.CampaignService;
import com.catalog.importer.entity.Campaign;
import com.catalog.importer.repository.CampaignRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignServiceImpl implements CampaignService {

    private CampaignRepository campaignRepository;

    public CampaignServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public Campaign save(Campaign campaign) {
        return campaignRepository.save(campaign);
    }


}

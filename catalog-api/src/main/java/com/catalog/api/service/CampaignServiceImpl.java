package com.catalog.api.service;

import com.catalog.api.repository.CampaignRepository;
import com.catalog.api.service.CampaignService;
import com.catalog.api.entity.Campaign;

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

    @Override
    public Optional<Campaign> findCampaignById(String id) {
        return campaignRepository.findById(id);
    }

    @Override
    public Optional<Campaign> listActiveCampaign() {
        return campaignRepository.findAllByActive(true);
    }

    @Override
    public List<Campaign> listAllCampaign() {
        return campaignRepository.findAll();
    }

    @Override
    public void deleteCampaign(String id) {
        campaignRepository.deleteById(id);
    }
}

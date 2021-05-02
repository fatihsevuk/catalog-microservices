package com.catalog.api.service;

import com.catalog.api.entity.Campaign;
import com.catalog.api.repository.CampaignRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@ExtendWith(MockitoExtension.class)
class CampaignServiceTest {

    @Mock
    private CampaignRepository campaignRepository;

    @InjectMocks
    CampaignServiceImpl campaignService;

    @Test
    void save() {
        Campaign campaign = new Campaign();
        given(campaignRepository.save(any(Campaign.class))).willReturn(campaign);

        Campaign savedCampaign = campaignService.save(new Campaign());

        then(campaignRepository).should().save(any(Campaign.class));
        assertThat(savedCampaign).isNotNull();
    }

    @Test
    void findCampaignById() {
        Campaign campaign=new Campaign();
        given(campaignRepository.findById(anyString())).willReturn(Optional.of(campaign));

        Optional<Campaign> foundCampaign = campaignService.findCampaignById(anyString());

        then(campaignRepository).should().findById(anyString());
        assertThat(foundCampaign.orElseGet(null)).isNotNull();

    }

    @Test
    void listActiveCampaign() {
        Campaign campaign=new Campaign();
        campaign.setActive(true);
        given(campaignRepository.findAllByActive(true)).willReturn(Optional.of(campaign));

        Optional<Campaign> foundCampaign = campaignService.listActiveCampaign();

        then(campaignRepository).should().findAllByActive(true);
        assertThat(foundCampaign.orElseGet(null).isActive()).isEqualTo(true);
    }

    @Test
    void listAllCampaign() {

        Campaign campaign=new Campaign();
        List<Campaign> campaignList=new ArrayList<>();
        campaignList.add(campaign);
        given(campaignRepository.findAll()).willReturn(campaignList);

        List<Campaign> foundCampaigns = campaignService.listAllCampaign();

        then(campaignRepository).should().findAll();
        assertThat(foundCampaigns).hasSize(1);

    }

    @Test
    void deleteCampaign() {
        Campaign campaign = new Campaign();
        campaign.setId("1");
        campaignService.deleteCampaign(campaign.getId());
        then(campaignRepository).should().deleteById(anyString());
    }
}
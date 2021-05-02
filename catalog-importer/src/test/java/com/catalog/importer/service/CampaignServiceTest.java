package com.catalog.importer.service;

import com.catalog.importer.entity.Campaign;
import com.catalog.importer.repository.CampaignRepository;
import com.catalog.importer.service.impl.CampaignServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CampaignServiceTest {


    @Mock
    private CampaignRepository campaignRepository;

    @InjectMocks
    private CampaignServiceImpl campaignService;

    @Test
    void save() {

        Campaign campaign=new Campaign();
        given(campaignRepository.save(any(Campaign.class))).willReturn(campaign);

        Campaign savedCamapign = campaignService.save(new Campaign());

        then(campaignRepository).should().save(any(Campaign.class));
        assertThat(savedCamapign).isNotNull();

    }
}
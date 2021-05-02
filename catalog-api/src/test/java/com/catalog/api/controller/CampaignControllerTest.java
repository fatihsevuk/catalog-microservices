package com.catalog.api.controller;

import com.catalog.api.config.ModelMapperConfig;
import com.catalog.api.config.ModelMapperTestConfig;
import com.catalog.api.entity.Campaign;
import com.catalog.api.entity.Product;
import com.catalog.api.model.CampaignCatalog;
import com.catalog.api.model.CampaignItem;
import com.catalog.api.service.CampaignService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CampaignController.class)
@Import(ModelMapperTestConfig.class)
class CampaignControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ModelMapper modelMapper;

    @MockBean
    CampaignService campaignService;

    List<Campaign> campaignList;
    CampaignItem campaignItem;
    Campaign campaign;
    CampaignCatalog catalog;

    @BeforeEach
    void setUp() {
        campaignList=new ArrayList<>();
        this.campaign=new Campaign();
        campaign.setId("13");
        campaign.setActive(true);
        campaign.setDescription("All products have %45 discount");
        campaign.setName("Last chance...");
        campaign.setStartDate(LocalDate.of(2021, Month.MAY, 2));
        campaign.setEndDate(LocalDate.of(2021, Month.MAY,17));

        Product product1 = new Product();
        product1.setId("12");
        product1.setName("Black T-Shirt");
        product1.setPrice(BigDecimal.valueOf(123));

        Product product2 = new Product();
        product2.setId("45");
        product2.setName("White T-Shirt");
        product2.setPrice(BigDecimal.valueOf(456));

        campaign.setProducts(Arrays.asList(product1,product2));

        ModelMapper mapper=new ModelMapper();
        campaignList.add(campaign);

        campaignItem = mapper.map(campaign,CampaignItem.class);

    }

    @Test
    void getAllCampaigns() throws Exception {
        given(this.campaignService.listAllCampaign()).willReturn(campaignList);
        given(modelMapper.map(campaign,CampaignItem.class)).willReturn(campaignItem);

        this.mockMvc.perform(get("/api/campaigns")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.campaigns[0].description").value("All products have %45 discount"));
    }

    @Test
    void getCampaign() throws Exception{
        given(this.campaignService.findCampaignById("13")).willReturn(Optional.of(campaign));
        given(modelMapper.map(campaign,CampaignItem.class)).willReturn(campaignItem);

        this.mockMvc.perform(get("/api/campaigns/13")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.products[0].name").value("Black T-Shirt"));

    }

    @Test
    void getCampaignProducts() throws Exception{
        given(this.campaignService.findCampaignById("13")).willReturn(Optional.of(campaign));
        given(modelMapper.map(campaign,CampaignItem.class)).willReturn(campaignItem);

        this.mockMvc.perform(get("/api/campaigns/13/products")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.products[0].price").value(123));

    }
}
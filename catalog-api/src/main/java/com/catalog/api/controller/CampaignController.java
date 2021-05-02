package com.catalog.api.controller;



import com.catalog.api.entity.Campaign;
import com.catalog.api.model.CampaignCatalog;
import com.catalog.api.model.CampaignItem;
import com.catalog.api.model.CampaignProducts;
import com.catalog.api.model.ProductDetailModel;
import com.catalog.api.service.CampaignService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CampaignController implements CampaignOperation {

    private CampaignService campaignService;
    private ModelMapper mapper;

    public CampaignController(CampaignService campaignService, ModelMapper mapper) {
        this.campaignService = campaignService;
        this.mapper = mapper;
    }


    @Override
    public ResponseEntity<CampaignCatalog> getAllCampaigns() {
        try {
            CampaignCatalog campaignCatalog=new CampaignCatalog();
            List<Campaign> campaignList = campaignService.listAllCampaign();
            campaignList.forEach(campaign -> {
                CampaignItem campaignItem= mapper.map(campaign,CampaignItem.class);
                campaignCatalog.getCampaigns().add(campaignItem);
            });
            return new ResponseEntity(campaignCatalog, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<CampaignItem> getCampaign(String campaignId) {
        try {
            if(campaignId.equals("") || campaignId==null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                Campaign campaign = campaignService.findCampaignById(campaignId).orElseThrow(() -> new Exception("(Id not found - " + campaignId));
                CampaignItem campaignItem = mapper.map(campaign,CampaignItem.class);
                return new ResponseEntity(campaignItem, HttpStatus.OK);
            }
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<CampaignProducts> getCampaignProducts(String campaignId) {
        try {
            CampaignProducts campaignProducts=new CampaignProducts();
            if(campaignId.equals("") || campaignId==null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                Campaign campaign = campaignService.findCampaignById(campaignId).orElseThrow(() -> new Exception("(Id not found - " + campaignId));
                campaignProducts.setProducts(mapper.map(campaign,CampaignItem.class).getProducts());
                return new ResponseEntity(campaignProducts, HttpStatus.OK);
            }
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}

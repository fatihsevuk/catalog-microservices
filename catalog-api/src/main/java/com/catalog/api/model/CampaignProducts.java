package com.catalog.api.model;

import lombok.Data;

import java.util.List;

@Data
public class CampaignProducts {
    private List<ProductDetailModel> products;
}

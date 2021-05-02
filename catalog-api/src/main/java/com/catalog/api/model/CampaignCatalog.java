package com.catalog.api.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CampaignCatalog {
    private List<CampaignItem> campaigns=new ArrayList<>();
}

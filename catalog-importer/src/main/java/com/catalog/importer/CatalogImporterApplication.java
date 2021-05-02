package com.catalog.importer;

import com.catalog.importer.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogImporterApplication{

    public static void main(String[] args) {
        SpringApplication.run(CatalogImporterApplication.class, args);
    }


}

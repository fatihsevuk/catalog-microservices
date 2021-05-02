package com.catalog.api.model;

import com.catalog.api.entity.Product;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;

@Data
public class CampaignItem {
    private String id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<ProductDetailModel> products;
    private String description;
    private boolean active;
}

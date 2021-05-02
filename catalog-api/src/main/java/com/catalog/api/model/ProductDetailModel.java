package com.catalog.api.model;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDetailModel {
    private String id;
    private String name;
    private BigDecimal price;
}

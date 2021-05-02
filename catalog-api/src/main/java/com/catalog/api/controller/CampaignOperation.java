package com.catalog.api.controller;

import com.catalog.api.model.CampaignCatalog;
import com.catalog.api.model.CampaignItem;
import com.catalog.api.model.CampaignProducts;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/campaigns")
@Api("/api/campaigns")
public interface CampaignOperation {


    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Get All Campaigns", notes = "Get all campaign info",response = CampaignCatalog.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Catalog successfully fetched", response = CampaignCatalog.class),
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 401, message = "Unauthorized access, please sign-in again"),
            @ApiResponse(code = 403, message = "Forbidden access, you cannot access this info"),
            @ApiResponse(code = 404, message = "We don't have info you want "),
            @ApiResponse(code = 500, message = "Ops. Something went wrong!"),
            @ApiResponse(code = 501, message = "Ops. We do not have this feature yet!"),
            @ApiResponse(code = 503, message = "Ops. This service is temporarily unavailable, try again later!"),

    })
    ResponseEntity<?> getAllCampaigns();


    @GetMapping(value="/{campaignId}",produces = "application/json")
    @ApiOperation(value = "Get campaign by id", notes = "Get campaign by id from path",response = CampaignItem.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Campaign successfully fetched", response = CampaignItem.class),
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 401, message = "Unauthorized access, please sign-in again"),
            @ApiResponse(code = 403, message = "Forbidden access, you cannot access this info"),
            @ApiResponse(code = 404, message = "We don't have info you want "),
            @ApiResponse(code = 500, message = "Ops. Something went wrong!"),
            @ApiResponse(code = 501, message = "Ops. We do not have this feature yet!"),
            @ApiResponse(code = 503, message = "Ops. This service is temporarily unavailable, try again later!"),

    })
    ResponseEntity<?> getCampaign(@ApiParam(required = true, name = "campaignId", value = "CampaignId for get campaign")
                              @PathVariable("campaignId") String campaignId);




    @GetMapping(value="/{campaignId}/products",produces = "application/json")
    @ApiOperation(value = "Get campaign products by campaignId", notes = "Get products of id spesific campaign",response = CampaignProducts.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Campaign successfully fetched", response = CampaignItem.class),
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 401, message = "Unauthorized access, please sign-in again"),
            @ApiResponse(code = 403, message = "Forbidden access, you cannot access this info"),
            @ApiResponse(code = 404, message = "We don't have info you want "),
            @ApiResponse(code = 500, message = "Ops. Something went wrong!"),
            @ApiResponse(code = 501, message = "Ops. We do not have this feature yet!"),
            @ApiResponse(code = 503, message = "Ops. This service is temporarily unavailable, try again later!"),

    })
    ResponseEntity<?> getCampaignProducts(@ApiParam(required = true, name = "campaignId", value = "CampaignId for get campaign")
                                  @PathVariable("campaignId") String campaignId);



}


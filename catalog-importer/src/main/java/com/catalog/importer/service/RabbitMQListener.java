package com.catalog.importer.service;

import com.catalog.importer.entity.Campaign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQListener {
    private static final Logger log = LoggerFactory.getLogger(RabbitMQListener.class);


    private CampaignService campaignService;

    public RabbitMQListener(CampaignService campaignService) {
        this.campaignService = campaignService;
    }


    @RabbitListener(queues ="${importerapi.rabbitmq.queueName}")
    public void receiveMessage(final Campaign campaign) {
        try {
            if(!campaign.equals(null)) {
                campaignService.save(campaign);
            }
        }catch(Exception e) {
            log.error("Error while receiving and save message"+e.getMessage());
        }
    }
}

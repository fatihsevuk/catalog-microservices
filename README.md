We have 2 service
* catalog-api
* catalog-importer

Database
* MongoDB

Service Communication
* RabbitMQ

Description
* Catalog-importer listens message queue for new campaign.And save incoming campaign to db.
* Catalog-api service exposes campaign api
    * GET /api/campaigns/
    * GET /api/campaigns/campaignId
    * GET /api/campaigns/campaignId/products
 

Our Api Doc
* campaign-api http://localhost:8080/swagger-ui.html


How to run
* instal docker-compose
* docker-compose up

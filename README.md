# GraphQL Schema Stitching with Lilo

This project demonstrates GraphQL schema stitching for `user-service`, `product-service`, and `orderservice`, running on ports 8081, 8082, and 8083 respectively, into a single gateway endpoint using the Lilo configurable GraphQL stitching library. The gateway runs on port 8089, routing all GraphQL queries through a unified API.

## Features
- Combines multiple GraphQL schemas into a single endpoint
- Seamless integration of microservices with schema stitching
- Centralized GraphQL gateway for easier query handling

![GraphQL Schema Stitching](https://miro.medium.com/v2/resize:fit:1100/format:webp/1*aC7w8OOb7IZie7wSSM7Rsw.png)

# In the example below, the gateway (running at http://localhost:8089/graphql) allows us to query both the user-service and product-service in a single request.

![Demo](images/Capture.png)

## Loki Logs in Grafana Dashboard

Logs from the `user-service` microservice in a Grafana dashboard using **Loki** as the data source. The logs provide detailed output from the `user-service`, including information about repository configurations, test execution, and general service status.

#### Key Features:
- **Data Source**: Loki, used for log aggregation.
- **Service Filter**: Logs are filtered for the `application = user-service` label, ensuring that only logs specific to the `user-service` are shown.
- **Time Range**: The logs are displayed for the last 3 hours with a refresh interval of 10 seconds.
- **Log Details**: The logs contain various messages, including startup processes, test executions, and

![Demo](images/loki.png)  

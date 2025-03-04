![Amazon API Gateway](https://img.shields.io/badge/Amazon%20API%20Gateway-FF4F8B.svg?logo=Amazon-API-Gateway&logoColor=white) ![Amazon SQS](https://img.shields.io/badge/Amazon%20SQS-FF4F8B.svg?logo=Amazon-SQS&logoColor=white) ![AWS Lambda](https://img.shields.io/badge/AWS%20Lambda-FF9900.svg?logo=AWS-Lambda&logoColor=white) ![Go](https://img.shields.io/badge/Go-00ADD8.svg?logo=Go&logoColor=white) ![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF.svg?logo=Kotlin&logoColor=white) ![Node.js](https://img.shields.io/badge/Node.js-5FA04E.svg?logo=nodedotjs&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F.svg?logo=Spring-Boot&logoColor=white) ![Next.js](https://img.shields.io/badge/Next.js-000000.svg?logo=nextdotjs&logoColor=white)

# POSCOMP Monitoring

This project was created to monitor the opening of the POSCOMP exam registration notice, which is a test for the selection of candidates for the master's and doctorate courses in Computer Science in Brazil.

## Architecture

![Architecture](.github/poscomp%20monitoring.png)

## Configuration

### AWS Credentials

1. Create `credentials` file in the `~/.aws` directory. If the `~/.aws` directory does not exist, create it.
2. Add the following content to the `credentials` file:

```credentials
[default]
aws_access_key_id = <YOUR_ACCESS_KEY_ID>
aws_secret_access_key = <YOUR_SECRET_ACCESS_KEY>
```

3. Replace `<YOUR_ACCESS_KEY_ID>` and `<YOUR_SECRET_ACCESS_KEY>` with your AWS credentials and save the file.

### AWS Lambda

1. Create a new Lambda function in the AWS
2. Copy the code from the `poscomp_cloud_awslambda_function/index.mjs` file and paste it into the Lambda function.

### Amazon API Gateway

1. Add a new trigger to the Lambda function.
2. Obtain the API Gateway URL.
3. Add the API Gateway URL to the `poscomp_backend_webscraping/src/main/resources/application.yaml` file:

```yaml
spring:
  application:
    name: poscomp_backend_webscraping

server:
  port: 3030

http-service:
  url: <API_ENDPOINT_GATEWAY>
  which-lambda-service: aws-lambda
```

### Amazon Simple Queue Service (SQS)

1. Create a new SQS queue in the AWS named `poscomp-queue`.
2. Add destination to the Lambda function.
3. Add the following content on `.env` file:

```.env
AWS_REGION=<YOUR_AWS_REGION>
AWS_SQS_QUEUE_URL=<YOUR_AWS_SQS_QUEUE_URL>
PORT_WEBSOCKET_SERVER=<PORT_WEBSOCKET_SERVER_NUMBER>
```

4. Replace `<YOUR_AWS_REGION>` with your AWS region, replace `<YOUR_AWS_SQS_QUEUE_URL>` with your SQS queue URL, and replace `<PORT_WEBSOCKET_SERVER_NUMBER>` with the port number of the WebSocket server and save the file.

## Running

### WebScraping (*poscomp_backend_webscraping*)

1. Open the `poscomp_backend_webscraping` project in IntelliJ IDEA.
2. Run the `PoscompBackendWebscrapingApplication.kt` file.

### WebSocket Server (*poscomp_backend_websocket_server*)

1. Open the terminal on the `poscomp_backend_websocket_server` directory.
2. Run the following command:

```bash
go run *.go
```

### WebSocket Client (*poscomp_frontend_websocket_client*)

1. Open the terminal on the `poscomp_frontend_websocket_client` directory.
2. Run the following command:

```bash
npm i
npm run dev
```


## License

This project is not licensed yet.

![Amazon API Gateway](https://img.shields.io/badge/Amazon%20API%20Gateway-FF4F8B.svg?logo=Amazon-API-Gateway&logoColor=white) ![Amazon SQS](https://img.shields.io/badge/Amazon%20SQS-FF4F8B.svg?logo=Amazon-SQS&logoColor=white) ![AWS Lambda](https://img.shields.io/badge/AWS%20Lambda-FF9900.svg?logo=AWS-Lambda&logoColor=white) ![Go](https://img.shields.io/badge/Go-00ADD8.svg?logo=Go&logoColor=white) ![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF.svg?logo=Kotlin&logoColor=white) ![Node.js](https://img.shields.io/badge/Node.js-5FA04E.svg?logo=nodedotjs&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F.svg?logo=Spring-Boot&logoColor=white) ![Next.js](https://img.shields.io/badge/Next.js-000000.svg?logo=nextdotjs&logoColor=white)

# 📊 POSCOMP Monitoring

POSCOMP Monitoring is a system designed to track the release of the POSCOMP exam registration notice. POSCOMP is a standardized test used for the selection of candidates for master's and doctoral programs in Computer Science in Brazil.

## 🛠️ Architecture

![Architecture](.github/poscomp%20monitoring.png)

The system consists of three main components:

- **Web Scraping Service (Kotlin, Spring Boot)**: Monitors the official POSCOMP website and sends updates to the AWS infrastructure.
- **WebSocket Server (Go)**: Broadcasts notifications to connected clients in real-time.
- **WebSocket Client (Next.js)**: Displays real-time updates to users via a web interface.

The infrastructure leverages AWS Lambda, Amazon API Gateway, and Amazon Simple Queue Service (SQS) for scalable and event-driven processing.

## ⚙️ Configuration

### 1. AWS Credentials

Ensure your AWS credentials are properly configured:

1. Create a `credentials` file in the `~/.aws` directory (create the directory if it does not exist).
2. Add the following content:

```ini
[default]
aws_access_key_id = <YOUR_ACCESS_KEY_ID>
aws_secret_access_key = <YOUR_SECRET_ACCESS_KEY>
```

Replace `<YOUR_ACCESS_KEY_ID>` and `<YOUR_SECRET_ACCESS_KEY>` with your AWS credentials.

### 2. AWS Lambda Setup

1. Create a new Lambda function in AWS.
2. Copy the code from `poscomp_cloud_awslambda_function/index.mjs` and paste it into the Lambda function.

### 3. Amazon API Gateway Setup

1. Add a new trigger to the Lambda function via the API Gateway.
2. Obtain the API Gateway URL.
3. Update the `application.yaml` file in `poscomp_backend_webscraping` with the API Gateway URL:

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

Replace `<API_ENDPOINT_GATEWAY>` with your API Gateway URL.

### 4. Amazon SQS Setup

1. Create a new SQS queue in AWS named `poscomp-queue`.
2. Configure the Lambda function to send messages to this queue.
3. Add the following content to the `.env` file:

```env
AWS_REGION=<YOUR_AWS_REGION>
AWS_SQS_QUEUE_URL=<YOUR_AWS_SQS_QUEUE_URL>
PORT_WEBSOCKET_SERVER=<PORT_WEBSOCKET_SERVER_NUMBER>
```

Replace the placeholders with your AWS region, SQS queue URL, and WebSocket server port number.

## 🚀 Running the System

### 1. Web Scraping Service (*poscomp_backend_webscraping*)

1. Open the `poscomp_backend_webscraping` project in IntelliJ IDEA.
2. Run the `PoscompBackendWebscrapingApplication.kt` file.

### 2. WebSocket Server (*poscomp_backend_websocket_server*)

1. Open a terminal and navigate to the `poscomp_backend_websocket_server` directory.
2. Start the WebSocket server:

```bash
go run *.go
```

### 3. WebSocket Client (*poscomp_frontend_websocket_client*)

1. Open a terminal and navigate to the `poscomp_frontend_websocket_client` directory.
2. Install dependencies and start the development server:

```bash
npm install
npm run dev
```

## 📚 License

This project is not licensed yet.

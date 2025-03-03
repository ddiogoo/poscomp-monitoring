![Amazon API Gateway](https://img.shields.io/badge/Amazon%20API%20Gateway-FF4F8B.svg?logo=Amazon-API-Gateway&logoColor=white) ![Amazon SQS](https://img.shields.io/badge/Amazon%20SQS-FF4F8B.svg?logo=Amazon-SQS&logoColor=white) ![AWS Lambda](https://img.shields.io/badge/AWS%20Lambda-FF9900.svg?logo=AWS-Lambda&logoColor=white) ![Go](https://img.shields.io/badge/Go-00ADD8.svg?logo=Go&logoColor=white) ![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF.svg?logo=Kotlin&logoColor=white) ![Node.js](https://img.shields.io/badge/Node.js-5FA04E.svg?logo=nodedotjs&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F.svg?logo=Spring-Boot&logoColor=white) ![Next.js](https://img.shields.io/badge/Next.js-000000.svg?logo=nextdotjs&logoColor=white)

# POSCOMP Monitoring

This project was created to monitor the opening of the POSCOMP exam registration notice, which is a test for the selection of candidates for the master's and doctorate courses in Computer Science in Brazil.

## Configuration

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

## License

This project is not licensed yet.

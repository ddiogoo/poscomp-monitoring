package main

import (
	"errors"
	"fmt"
	"log"
	"os"
	"os/signal"
	"syscall"
	"time"

	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/aws/credentials"
	"github.com/aws/aws-sdk-go/aws/session"
	"github.com/aws/aws-sdk-go/service/sqs"
)

var QUEUE_URL string

type AwsSqsClient struct {
	svc *sqs.SQS
	hub *Hub
}

func NewAwsSqsClient(hub *Hub) (*AwsSqsClient, error) {
	sess, err := session.NewSession(&aws.Config{
		Region:      aws.String(os.Getenv("AWS_REGION")),
		Credentials: credentials.NewSharedCredentials("", "default"),
	})
	if err != nil {
		return &AwsSqsClient{}, errors.New("error when retreiving aws credentials")
	}
	QUEUE_URL = os.Getenv("AWS_SQS_QUEUE_URL")
	return &AwsSqsClient{svc: sqs.New(sess), hub: hub}, nil
}

func (s *AwsSqsClient) HandleConsumingQueue() {
	log.Println("[*] Waiting messages...")
	signalCh := make(chan os.Signal, 1)
	signal.Notify(signalCh, syscall.SIGINT, syscall.SIGTERM, syscall.SIGQUIT)
	for {
		select {
		case <-signalCh:
			fmt.Println("Exiting")
			return
		default:
			receiveParams := &sqs.ReceiveMessageInput{
				MaxNumberOfMessages: aws.Int64(1),
				QueueUrl:            aws.String(QUEUE_URL),
				WaitTimeSeconds:     aws.Int64(20),
			}
			result, err := s.svc.ReceiveMessage(receiveParams)
			if err != nil {
				fmt.Println(err)
				time.Sleep(1 * time.Second)
				continue
			}
			for _, msg := range result.Messages {
				fmt.Printf("[*] Received message: %s\n", *msg.Body)
				s.hub.broadcast <- []byte(*msg.Body)
				deleteParams := &sqs.DeleteMessageInput{
					QueueUrl:      aws.String(QUEUE_URL),
					ReceiptHandle: msg.ReceiptHandle,
				}
				_, err := s.svc.DeleteMessage(deleteParams)
				if err != nil {
					fmt.Println(err)
					time.Sleep(1 * time.Second)
					continue
				}
			}
		}
	}
}

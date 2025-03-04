package main

import (
	"flag"
	"log"
	"net/http"
	"os"

	"github.com/joho/godotenv"
)

func main() {
	flag.Parse()
	err := godotenv.Load()
	if err != nil {
		log.Fatal("Error loading .env file")
	}
	hub := newHub()
	sqs, err := NewAwsSqsClient(hub)
	if err != nil {
		log.Fatalf("Error while instance Amazon SQS Client: %v\n", err)
	}
	go hub.run()
	go sqs.HandleConsumingQueue()
	http.HandleFunc("/ws", func(w http.ResponseWriter, r *http.Request) {
		serveWs(hub, w, r)
	})
	addr := flag.String("addr", ":"+os.Getenv("PORT_WEBSOCKET_SERVER"), "http service address")
	err = http.ListenAndServe(*addr, nil)
	if err != nil {
		log.Fatal("ListenAndServe: ", err)
	}
}

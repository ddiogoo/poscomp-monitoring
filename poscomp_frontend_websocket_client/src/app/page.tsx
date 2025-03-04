"use client";

import { useState } from "react";
import useWebSocket from "react-use-websocket";

export default function Home() {
  const [messages, setMessages] = useState<string[]>([]);
  useWebSocket("ws://localhost:5050/ws", {
    onMessage: (event) => {
      const message = event.data;
      setMessages((messages) => [...messages, message]);
    },
    onOpen: () => {
      console.log("connected");
    },
  });
  return (
    <div>
      {messages.map((message, index) => (
        <div key={index}>{message}</div>
      ))}
    </div>
  );
}

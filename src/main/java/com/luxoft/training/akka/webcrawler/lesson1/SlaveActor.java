package com.luxoft.training.akka.webcrawler.lesson1;

import akka.actor.AbstractActor;

public class SlaveActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("send", (x) -> {
                    System.out.println("Worked received");
                    getSender().tell("done", getContext().getSelf());
                })
                .matchAny(msg -> {
                    System.out.println("Unknown message from " + getSender());
                })
                .build();
    }
}

package com.luxoft.training.akka.webcrawler.actor;

import akka.actor.AbstractActor;

public class ContentAnalyzerActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder().
                build();
    }

}

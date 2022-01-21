package com.luxoft.training.akka.webcrawler.lesson1;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import com.luxoft.training.akka.webcrawler.lesson1.model.ClassMessage;

public class FirstActor extends AbstractActor {

    private final String name;
    private final ActorRef slave = getContext().actorOf(Props.create(SlaveActor.class));

    public FirstActor(String name) {
        this.name = name;
        System.out.println("My first actor created");
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("hello", this::onHello)
                .matchEquals("send", msg -> {slave.tell("send", getContext().getSelf());})
                .matchEquals("done", msg -> {
                    System.out.println("FirstActor: Job is done");
                })
                .match(Integer.class, msg -> {
                    System.out.println(msg + "+" + msg + "=" + (msg + msg) + "");
                })
                .matchAny(msg -> {
                    System.out.println("FirstActor: Unknown message from " + getSender());
                })
                .match(ClassMessage.class, msg -> {

                })

                .build();
    }

    private void onHello(String msg) {
        System.out.println("FirstActor: Recieved hello = " + msg);
    }
}

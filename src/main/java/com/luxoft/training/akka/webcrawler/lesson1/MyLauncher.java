package com.luxoft.training.akka.webcrawler.lesson1;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;


public class MyLauncher {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("MyFirstSystem");
        ActorRef firstActor = system.actorOf(Props.create(FirstActor.class, "test"));

        firstActor.tell("hello", null);
        firstActor.tell("send", null);

        system.terminate();
    }
}

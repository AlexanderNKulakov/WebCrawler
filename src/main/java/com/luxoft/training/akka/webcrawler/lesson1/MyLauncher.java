package com.luxoft.training.akka.webcrawler.lesson1;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.luxoft.training.akka.webcrawler.actor.JobSchedulerActor;
import com.luxoft.training.akka.webcrawler.message.UrlMessage;


public class MyLauncher {

    public static void main(String[] args) {

        ActorSystem system = ActorSystem.create("MyFirstSystem");

     //   ActorRef firstActor = system.actorOf(Props.create(FirstActor.class, "test"));
     //   firstActor.tell("hello", null);
     //   firstActor.tell("send", null);


        ActorRef jobSchedulerActor = system.actorOf(Props.create(JobSchedulerActor.class, "test"));
   //     jobSchedulerActor.tell("hello", null);
        jobSchedulerActor.tell(new UrlMessage("https://doc.akka.io/docs/akka/current/index-classic.html"), null);

        system.terminate();
    }
}

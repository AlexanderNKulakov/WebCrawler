package com.luxoft.training.akka.webcrawler.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import com.luxoft.training.akka.webcrawler.message.ContentMessage;
import com.luxoft.training.akka.webcrawler.message.KeywordMessage;
import com.luxoft.training.akka.webcrawler.message.UrlMessage;

public class JobSchedulerActor extends AbstractActor {

    private final String name;

    private final ActorRef urlDownloaderActor = getContext().actorOf(Props.create(UrlDownloaderActor.class));
    private final ActorRef htmlParserActor = getContext().actorOf(Props.create(HtmlParserActor.class));
    private final ActorRef contentAnalyzerActor = getContext().actorOf(Props.create(ContentAnalyzerActor.class));
    private final ActorRef indexingServiceActor = getContext().actorOf(Props.create(IndexingServiceActor.class));

    public JobSchedulerActor(String name) {
        this.name = name;
        System.out.println("JobSchedulerActor created");
    }

    @Override
    public Receive createReceive() {
        System.out.println("createReceive from JobSchedulerActor");
        return receiveBuilder()
                .matchEquals("hello", this::onHello)
                .match(UrlMessage.class, msg -> {
                    urlDownloaderActor.tell(msg, getContext().getSelf());
                })
                .match(ContentMessage.class, msg -> {
                    htmlParserActor.tell(msg, getContext().getSelf());
                })
                .match(KeywordMessage.class, msg -> {
                    contentAnalyzerActor.tell("analyze", getContext().getSelf());
                })
                /*      .match("indexing", msg -> {
                          indexingServiceActor.tell("indexing", getContext().getSelf());
                  })*/
                .build();
    }

    private void onHello(String msg) {
        System.out.println("JobSchedulerActor: Recieved hello = " + msg);
    }
}

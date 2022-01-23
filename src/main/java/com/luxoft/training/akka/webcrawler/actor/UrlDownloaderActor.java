package com.luxoft.training.akka.webcrawler.actor;

import akka.actor.AbstractActor;
import com.luxoft.training.akka.webcrawler.message.ContentMessage;
import com.luxoft.training.akka.webcrawler.message.UrlMessage;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.logging.Logger;

public class UrlDownloaderActor extends AbstractActor {

    private static Logger LOGGER = Logger.getLogger(UrlDownloaderActor.class.getName());

    private String name;

    public UrlDownloaderActor() {
    }

    public UrlDownloaderActor(String name) {
        this.name = name;
        System.out.println("UrlDownloaderActor created");
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(UrlMessage.class, this::onDownload)
                .build();
    }

    private void onDownload(UrlMessage urlMessage) {
        System.out.println("onDownload: url " + urlMessage.getUrl());
        String content = null;
        try {
            content = Jsoup.connect(urlMessage.getUrl())
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:89.0) Gecko/20100101 Firefox/89.0")
                    .get()
                    .toString();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        ContentMessage contentMessage = new ContentMessage(urlMessage.getUrl(), content);
        getSender().tell(contentMessage, getContext().getSelf());
        System.out.println("onDownload: finished");
    }
}

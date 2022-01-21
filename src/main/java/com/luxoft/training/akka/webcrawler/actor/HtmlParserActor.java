package com.luxoft.training.akka.webcrawler.actor;

import akka.actor.AbstractActor;
import com.luxoft.training.akka.webcrawler.message.ContentMessage;
import com.luxoft.training.akka.webcrawler.message.KeywordMessage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParserActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        System.out.println("createReceive from HtmlParserActor");
        return receiveBuilder()
                .match(ContentMessage.class, this::onParse)
                .build();
    }

    private void onParse(ContentMessage contentMessage) {
//        LOGGER.log(Level.INFO, "url = " + url);
        System.out.println("onParse: url " + contentMessage.getUrl());
        Document doc = Jsoup.parse(contentMessage.getContent(), contentMessage.getUrl());
        Elements titles = doc.select("title");
        for (Element title : titles) {
            String text = title.text();
            System.out.println("text: " + text);
            KeywordMessage keywordMessage = new KeywordMessage(contentMessage.getUrl(), text);
            getSender().tell(keywordMessage, getContext().getSelf());
        }
    }
}

package com.luxoft.training.akka.webcrawler.actor;

import akka.actor.AbstractActor;
import com.luxoft.training.akka.webcrawler.message.ContentMessage;
import com.luxoft.training.akka.webcrawler.message.KeywordMessage;
import com.luxoft.training.akka.webcrawler.message.UrlMessage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParserActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ContentMessage.class, this::onParse)
                .build();
    }

    private void onParse(ContentMessage contentMessage) {
//        LOGGER.log(Level.INFO, "url = " + url);
        System.out.println("onParse: url " + contentMessage.getUrl());
        Document doc = Jsoup.parse(contentMessage.getContent(), contentMessage.getUrl());
        Elements links = doc.select("a[href]");
        for (Element link : links) {
            String url = link.attr("abs:href");
            System.out.println("onParse: url: " + url);
            UrlMessage urlMessage = new UrlMessage(url);
            getSender().tell(urlMessage, getContext().getSelf());
        }
    }
}

package com.luxoft.training.akka.webcrawler.actor;

import akka.actor.AbstractActor;
import com.luxoft.training.akka.webcrawler.H2Helper;
import com.luxoft.training.akka.webcrawler.message.ContentMessage;
import com.luxoft.training.akka.webcrawler.message.KeywordMessage;
import com.luxoft.training.akka.webcrawler.message.UrlMessage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.SQLException;

public class IndexingServiceActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(KeywordMessage.class, this::onIndexing)
                .build();
    }

    private void onIndexing(KeywordMessage keywordMessage) {
        System.out.println("onIndexing: url " + keywordMessage.getUrl());
        try {
            H2Helper.persistKeyword(keywordMessage.getUrl(), keywordMessage.getKeyword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

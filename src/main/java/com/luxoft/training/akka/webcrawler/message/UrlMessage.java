package com.luxoft.training.akka.webcrawler.message;

/**
 *
 * @author Orkhan Gasimov
 */
public class UrlMessage {

    private final String url;

    public UrlMessage(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}

package com.luxoft.training.akka.webcrawler.message;

/**
 *
 * @author Orkhan Gasimov
 */
public class KeywordMessage {
    private final String url;
    private final String keyword;

    public KeywordMessage(String url, String keyword) {
        this.url = url;
        this.keyword = keyword;
    }

    public String getUrl() {
        return url;
    }

    public String getKeyword() {
        return keyword;
    }
}

package com.luxoft.training.akka.webcrawler.message;

/**
 *
 * @author Orkhan Gasimov
 */
public class ContentMessage {
    private final String url;
    private final String content;

    public ContentMessage(String url, String content) {
        this.url = url;
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }
}

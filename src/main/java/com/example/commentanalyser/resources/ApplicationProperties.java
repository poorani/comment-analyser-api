package com.example.commentanalyser.resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {
    @Value("${youtube.key}")
    String youtubeKey;
    @Value("${youtube.search.url1}")
    String youtubeSearchUrl1;
    @Value("${youtube.search.url2}")
    String youtubeSearchUrl2;

    public String getYoutubeKey() {
        return youtubeKey;
    }

    public void setYoutubeKey(String youtubeKey) {
        this.youtubeKey = youtubeKey;
    }

    public String getYoutubeSearchUrl1() {
        return youtubeSearchUrl1;
    }

    public void setYoutubeSearchUrl1(String youtubeSearchUrl1) {
        this.youtubeSearchUrl1 = youtubeSearchUrl1;
    }

    public String getYoutubeSearchUrl2() {
        return youtubeSearchUrl2;
    }

    public void setYoutubeSearchUrl2(String youtubeSearchUrl2) {
        this.youtubeSearchUrl2 = youtubeSearchUrl2;
    }
}

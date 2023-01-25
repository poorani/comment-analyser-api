package com.example.commentanalyser.controller;

import com.example.commentanalyser.model.*;
import com.example.commentanalyser.resources.ApplicationProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("commentAnalyser")
public class MainAppController {
    @Autowired
    ApplicationProperties applicationProperties;

    List<Video> videoList = new ArrayList<>();

    @GetMapping("/videoList/{searchText}")
    public List<Video> getVideoListByComment(@PathVariable String searchText) throws JsonProcessingException {
        String url = applicationProperties.getYoutubeSearchUrl1() + searchText + applicationProperties.getYoutubeSearchUrl2() + applicationProperties.getYoutubeKey();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            // Successful request
            String responseBody = response.getBody();
            ObjectMapper om = new ObjectMapper();
            YoutubeSearchResponse youtubeSearchResponse = om.readValue(responseBody,YoutubeSearchResponse.class);
            mapVideoList(youtubeSearchResponse);
            getCommentsByVideoId();
            sortVideoListByCommentCount();
            //return youtubeSearchResponse;
            return videoList;
        }
        else
         return new ArrayList<>();
    }




    private void mapVideoList(YoutubeSearchResponse youtubeSearchResponse){

        for(Item item : youtubeSearchResponse.getItems()){
            Video video = new Video();
            video.setId(item.getId().getVideoId());
            video.setCommentCount(0);
            videoList.add(video);
            System.out.println(video);
        }
    }

    private void getCommentsByVideoId() throws JsonProcessingException {
        for(Video video : videoList){
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://www.googleapis.com/youtube/v3/commentThreads?part=snippet&videoId=" + video.getId() + "&key=" + applicationProperties.getYoutubeKey();
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                // Successful request
                String json = response.getBody();
                ObjectMapper om = new ObjectMapper();
                video.setCommentCount(calculateTryCommentCount(om.readValue(json, YoutubeCommentThreadResponse.class)));
                System.out.println(video.getCommentCount());
            }
            else{
                System.out.println(response);
            }
        }
    }

    private int calculateTryCommentCount(YoutubeCommentThreadResponse youtubeCommentThreadResponse){
        List<String> commentList = new ArrayList<>();
        for(CommentThreadItem item : youtubeCommentThreadResponse.getItems()){
            if(item.getSnippet().getTopLevelComment().getSnippet().getTextOriginal().contains("try") ||
                    item.getSnippet().getTopLevelComment().getSnippet().getTextOriginal().contains("tried") ||
                    item.getSnippet().getTopLevelComment().getSnippet().getTextOriginal().contains("made"))
                         commentList.add ( item.getSnippet().getTopLevelComment().getSnippet().getTextOriginal());
        }
        return commentList.size();
    }
    private void sortVideoListByCommentCount() {
        videoList.sort((x, y) -> y.getCommentCount() - x.getCommentCount());
    }
}

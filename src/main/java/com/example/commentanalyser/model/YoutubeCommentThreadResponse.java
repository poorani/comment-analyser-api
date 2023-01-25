package com.example.commentanalyser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YoutubeCommentThreadResponse {

    String kind;
    String etag;
    String nextPageToken;
    PageInfo pageInfo;
    ArrayList<CommentThreadItem> items;
}





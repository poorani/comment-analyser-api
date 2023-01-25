package com.example.commentanalyser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YoutubeSearchResponse {
    String kind;
    String etag;
    String nextPageToken;
    String prevPageToken;
    String regionCode;
    PageInfo pageInfo;
    ArrayList<Item> items;
}


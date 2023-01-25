package com.example.commentanalyser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Snippet {
    String videoId;
    TopLevelComment topLevelComment;
    boolean canReply;
    int totalReplyCount;
    boolean isPublic;
    String textDisplay;
    String textOriginal;
    String authorDisplayName;
    String authorProfileImageUrl;
    String authorChannelUrl;
    AuthorChannelId authorChannelId;
    boolean canRate;
    String viewerRating;
    int likeCount;
    Date publishedAt;
    Date updatedAt;
}

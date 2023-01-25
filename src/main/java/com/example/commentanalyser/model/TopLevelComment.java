package com.example.commentanalyser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopLevelComment {
    String kind;
    String etag;
    String id;
    Snippet snippet;
}

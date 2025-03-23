package com.satya.postApp.entity;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "suvichars")
public class Suvichar {

    @Id
    private String id; // ID for the Suvichar entity

    private String quote; // Main quote text
    private String category; // e.g., motivational, spiritual, etc.
    private boolean goodMorningWish=false; // Boolean flag for "Good Morning" wishes

    private List<String> tags; // List of tags (e.g., ["motivational", "ram"])

    private List<Translation> translations; // List of translations

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Translation {
        private String language;  // Language name (e.g., English, Hindi)
        private String langCode;  // Language code (e.g., "en", "hi", "gu")
        private String quote;     // Translated quote text
    }
}
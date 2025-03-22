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

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "suvichars")
public class Suvichar {

    @Id
    private String id;

    @Indexed
    private Map<String, String> languages; // Supports 10 languages (e.g., hi, en, gu, etc.)

    private Style style;

    @Indexed
    private List<String> tags;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    // Inner Style Class
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Style {
        private String font;
        private int fontSize;
        private String color;

        // Getters and Setters
    }

    // Getters and Setters
}

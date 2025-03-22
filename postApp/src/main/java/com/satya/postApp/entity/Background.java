package com.satya.postApp.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data                         // Generates getters, setters, equals, hashCode, and toString
@NoArgsConstructor             // Generates a no-args constructor
@AllArgsConstructor            // Generates an all-args constructor
@Builder
@Document(collection = "backgroundImages")
public class Background {


    @Id
    private String id;

    @Indexed
    private String imageUrl;

    @Indexed
    private List<String> tags;

    @Indexed
    private String language;

    @Builder.Default
    private Instant createdAt = Instant.now();    // Auto-set current UTC time

    private Instant updatedAt;
}

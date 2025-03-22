package com.satya.postApp.dto;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuvicharDto {
    @NonNull
    private Map<String, String> languages; // Supports 10 languages (e.g., hi, en, gu, etc.)



    private List<String> tags;
}

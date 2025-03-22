package com.satya.postApp.service;

import com.satya.postApp.dto.TextDto;
import com.satya.postApp.entity.Suvichar;
import com.satya.postApp.repository.SuvicharRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class SuvicharService {

    @Autowired
    private SuvicharRepository suvicharRepository;
    @Autowired
    private TranslationsService translationService;

    private final List<String> targetLanguages = Arrays.asList("en", "hi", "bn", "mr", "te", "ta", "gu", "kn", "or", "pa", "ml");

    public List<Suvichar> getAllSuvichar()
    {
        return suvicharRepository.findAll();
    }

    public void translateAndSaveSuvichars(List<TextDto> suvichars) {
        for (TextDto suvichar : suvichars) {
            try {
                // Create translations map
                Map<String, String> translations = new HashMap<>();
                for (String lang : targetLanguages) {
                    // Use getter method instead of direct field access
                    String translatedText = translationService.translateText(suvichar.getText(), lang);
                    System.out.println(translatedText);
                    translations.put(lang, translatedText);
                }

                // Create default style
                Suvichar.Style style = new Suvichar.Style();
                // Set default values for style properties
                // e.g., style.setFont("Arial");

                // Create and save the Suvichar entity with all required fields
                Suvichar entity = Suvichar.builder()
                        .languages(translations)  // Set translations map
                        .style(style)             // Set default style
                        .tags(new ArrayList<>())  // Initialize empty tags list
                        .createdAt(Instant.now())  // Set createdAt timestamp
                        .updatedAt(Instant.now())  // Set updatedAt timestamp
                        .build();
                try {
                    Suvichar saved = suvicharRepository.save(entity);
                    System.out.println("Document saved successfully with ID: " + saved.toString());
                } catch (Exception e) {
                    System.err.println("Error saving document to MongoDB: " + e.getMessage());
                    e.printStackTrace();
                }
            } catch (Exception e) {
                // Log the error
                System.err.println("Error processing suvichar: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}

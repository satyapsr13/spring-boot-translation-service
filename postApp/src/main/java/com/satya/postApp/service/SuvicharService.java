package com.satya.postApp.service;

import com.satya.postApp.dto.SuvicharDto;
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

    public Suvichar addSuvichar(SuvicharDto dto) {
        // Create a default style or configure it from the DTO

        // Generate translations if the DTO contains text
        // Create and save the Suvichar entity using DTO fields
        Suvichar newSuvichar = Suvichar.builder()
                .languages(dto.getLanguages())  // Use provided translations map

                .tags(dto.getTags() != null ? dto.getTags() : new ArrayList<>())  // Handle null tags
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();


        return suvicharRepository.save(newSuvichar);
    }

    public void processAndSaveSuvichars(List<String> suvichars) {
        for (String originalSuvichar : suvichars) {
            try {
                // Step 1: Translate the Suvichar into 10 Indian languages
                Map<String, String> translations = new HashMap<>();
                for (String lang : targetLanguages) {
                    String translatedText = translationService.translateText(originalSuvichar, lang);
                    translations.put(lang, translatedText);
                }

                // Step 2: Generate tags using Azure Text Analytics
//                List<String> generatedTags = azureTagService.generateTags(originalSuvichar); // Call Azure API for tags
                List<String> generatedTags = new ArrayList<>(); // Call Azure API for tags

                // Ensure tags length is between 3 to 10
                if (generatedTags.size() < 3) {
                    generatedTags.add("motivation"); // Add default tag if too few
                }
                if (generatedTags.size() > 10) {
                    generatedTags = generatedTags.subList(0, 10); // Limit tags to max 10
                }

                // Step 3: Create and save Suvichar entity in MongoDB
                Suvichar suvicharEntity = Suvichar.builder()
                        .languages(translations)
                        .tags(generatedTags)
                        .createdAt(Instant.now())
                        .updatedAt(Instant.now())
                        .build();

                suvicharRepository.save(suvicharEntity);
                System.out.println("Suvichar saved successfully!");
            } catch (Exception e) {
                System.err.println("Error processing Suvichar: " + originalSuvichar + " - " + e.getMessage());
                e.printStackTrace();
            }
        }
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

                // Set default values for style properties
                // e.g., style.setFont("Arial");

                // Create and save the Suvichar entity with all required fields
                Suvichar entity = Suvichar.builder()
                        .languages(translations)  // Set translations map
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

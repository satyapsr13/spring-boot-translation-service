package com.satya.postApp.service;

import org.json.JSONArray;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class TranslationsService {

    private static final String AZURE_TRANSLATE_API_URL = "https://api.cognitive.microsofttranslator.com/translate?api-version=3.0";
    private static final String API_KEY = "A3YwrxEKB618F5H6QBlWHKvEDOcBcKiYo8r4yzjNLBULYmGrni7sJQQJ99BCACGhslBXJ3w3AAAbACOGW743";  // Replace with your Azure API key
    private static final String REGION = "centralindia";  // Replace with the Azure region, e.g., "eastus"
    public String translateText(String text, String targetLanguage) {
        try {
            // Define constants (should be moved to configuration)
          
            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Ocp-Apim-Subscription-Key", API_KEY);
            headers.set("Ocp-Apim-Subscription-Region", REGION);
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Create request body
            Map<String, String> textObject = new HashMap<>();
            textObject.put("Text", text);

            HttpEntity<Object> request = new HttpEntity<>(Collections.singletonList(textObject), headers);

            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.postForObject(
                    AZURE_TRANSLATE_API_URL + "&to=" + targetLanguage,
                    request,
                    String.class
            );

            // Parse response
            JSONArray jsonResponseArray = new JSONArray(response);
            return jsonResponseArray.getJSONObject(0)
                    .getJSONArray("translations")
                    .getJSONObject(0)
                    .getString("text");
        } catch (Exception e) {
            // Log error and handle exception appropriately
            System.err.println("Translation failed: " + e.getMessage());
            return "Translation error";
        }
    }
}

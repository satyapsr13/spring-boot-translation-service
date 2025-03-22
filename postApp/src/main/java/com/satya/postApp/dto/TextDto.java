package com.satya.postApp.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class TextDto {

    public String text;



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

package com.example.bakery.models.enums;

public enum PhotoSimilarityOptions {
    YES("Yes, please!"),
    NO("No, get creative!"),
    MAYBE("I'd like to talk more about how I envision the cake.");
    private final String label;

    PhotoSimilarityOptions(String label) {
        this.label = label;
    }
}

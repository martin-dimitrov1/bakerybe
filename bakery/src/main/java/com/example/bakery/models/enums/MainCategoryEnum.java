package com.example.bakery.models.enums;

public enum MainCategoryEnum {
    DESSERTS("Desserts"),
    STANDARD("Standard cakes"),
    CELEBRATION("Celebration cakes"),
    CATERING("Catering");

    private final String label;

    MainCategoryEnum(String s) {
        this.label = s;
    }

    public String getLabel() {
        return label;
    }
}

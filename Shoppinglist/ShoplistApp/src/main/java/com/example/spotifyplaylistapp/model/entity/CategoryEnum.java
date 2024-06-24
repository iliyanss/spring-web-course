package com.example.spotifyplaylistapp.model.entity;

public enum CategoryEnum {
    FOOD("Food"),
    DRINK("Drink"),
    HOUSEHOLD("Household"),
    OTHER("Other");
    private final String value;

    CategoryEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}

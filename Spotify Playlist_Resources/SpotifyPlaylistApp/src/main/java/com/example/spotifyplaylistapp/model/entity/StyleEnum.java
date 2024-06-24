package com.example.spotifyplaylistapp.model.entity;

public enum StyleEnum {
    POP("Pop"),
    ROCK("Rock"),
    JAZZ("Jazz");
    private final String value;

     StyleEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}

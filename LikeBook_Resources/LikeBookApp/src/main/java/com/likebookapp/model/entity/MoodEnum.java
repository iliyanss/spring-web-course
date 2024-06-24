package com.likebookapp.model.entity;

public enum MoodEnum {
    HAPPY("Happy"),
    SAD("Sad"),
    INSPIRED("Inspired");

    private final String value;

    MoodEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

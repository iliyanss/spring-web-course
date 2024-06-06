package com.dictionaryapp.model.entity;

public enum LanguageEnum {
    GERMAN("German"),
    SPANISH("Spanish"),
    FRENCH("French"),
    ITALIAN("Italian");

    private final String value;

    LanguageEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

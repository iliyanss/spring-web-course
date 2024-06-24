package com.paintingscollectors.model.entity;

public enum StyleEnum {
    IMPRESSIONISM("Impressionism"),
    ABSTRACT("Abstract"),
    EXPRESSIONISM("Expressionism"),
    SURREALISM("Surrealism"),
    REALISM("Realism");
    private final String value;

    StyleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

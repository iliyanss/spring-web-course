package com.bonappetit.model.entity;

public enum CategoryEnum {
    MAIN_DISH("MainDish"),
    DESSERT("Dessert"),
    COCKTAIL("Cocktail");

    private final String value;

    CategoryEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

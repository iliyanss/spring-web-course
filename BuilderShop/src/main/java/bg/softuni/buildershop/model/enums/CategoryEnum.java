package bg.softuni.buildershop.model.enums;

public enum CategoryEnum {

    REAL_ESTATE("REAL_ESTATE"),
    ELECTRONICS("ELECTRONICS"),
    BOOKS("BOOKS"),
    CLOTHES("CLOTHES"),
    HOME("HOME");
    private final String value;

    CategoryEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}

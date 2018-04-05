package domain;

public enum LengteStatus {
    MAAND("maand"),
    HALFJAAR("halfjaar"),
    JAAR("jaar");

    private String name;

    LengteStatus(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

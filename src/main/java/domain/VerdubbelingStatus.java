package domain;

public enum VerdubbelingStatus {
    STANDAARD("standaard"),
    VERDUBBELD("verdubbeld"),
    NIETBESCHIKBAAR("niet-beschikbaar");

    private String name;

    VerdubbelingStatus(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

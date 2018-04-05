package domain;

public enum AbonnementStatus {
    OPGEZEGD("ogpezegd"),
    ACTIEF("actief"),
    PROEF("proef");

    private String name;

    AbonnementStatus(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

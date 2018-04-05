package domain;

public enum AanbiederStatus {
    VODAFONE("vodafone"),
    ZIGGO("ziggo");

    private String name;

    AanbiederStatus(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

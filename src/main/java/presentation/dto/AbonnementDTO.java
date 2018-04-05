package presentation.dto;

import domain.AanbiederStatus;

public class AbonnementDTO {
    private int id;
    private String aanbieder;
    private String dienst;

    public AbonnementDTO(int id, AanbiederStatus aanbiederStatus, String dienst) {
        this.id = id;
        this.aanbieder = aanbiederStatus.getName();
        this.dienst = dienst;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAanbieder() {
        return aanbieder;
    }

    public void setAanbieder(AanbiederStatus aanbiederStatus) {
        this.aanbieder = aanbiederStatus.getName();
    }

    public String getDienst() {
        return dienst;
    }

    public void setDienst(String dienst) {
        this.dienst = dienst;
    }
}

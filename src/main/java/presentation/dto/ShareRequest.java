package presentation.dto;

public class ShareRequest {
    private int id;

    public ShareRequest(){}

    public ShareRequest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

package domain;

/**
 * Created by Christiaan on 4-2-2018.
 */
public abstract class DomainObject {
    private int id;

    public DomainObject(){

    }

    public DomainObject(int id){
        this.id = id;
    }

    public String toString() {
        return ("id: " + id + ". ");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

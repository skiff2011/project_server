package project_server;

/**
 * Created by aleks on 11.01.2017.
 */
public class ViolationKind {
    private int id;
    private String name;

    public ViolationKind() {
    }

    public ViolationKind(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

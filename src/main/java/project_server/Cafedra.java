package project_server;

/**
 * Created by aleks on 04.01.2017.
 */
public class Cafedra {
    private int id;
    private String name;
    private String shifr;

    public Cafedra(int id, String name, String shifr) {
        this.id = id;
        this.name = name;
        this.shifr = shifr;
    }

    public Cafedra() {
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

    public String getShifr() {
        return shifr;
    }

    public void setShifr(String shifr) {
        this.shifr = shifr;
    }
}

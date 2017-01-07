package project_server;

/**
 * Created by aleks on 04.01.2017.
 */
public class Speciality {
    private int id;
    private String cafedra;
    private String name;
    private String shifr;

    public Speciality() {
    }

    public Speciality(int id, String cafedra, String name, String shifr) {
        this.id = id;
        this.cafedra = cafedra;
        this.name = name;
        this.shifr = shifr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCafedra() {
        return cafedra;
    }

    public void setCafedra(String cafedra) {
        this.cafedra = cafedra;
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

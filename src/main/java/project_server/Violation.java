package project_server;

import java.util.Date;

/**
 * Created by aleks on 11.01.2017.
 */
public class Violation {
    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private String violationKind;
    private String punish;
    private Date date;

    public Violation() {
    }

    public Violation(int id, String surname, String name, String patronymic, String violationKind, String punish, Date date) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.violationKind = violationKind;
        this.punish = punish;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getViolationKind() {
        return violationKind;
    }

    public void setViolationKind(String violationKind) {
        this.violationKind = violationKind;
    }

    public String getPunish() {
        return punish;
    }

    public void setPunish(String punish) {
        this.punish = punish;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

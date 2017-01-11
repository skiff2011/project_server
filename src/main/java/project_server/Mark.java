package project_server;

/**
 * Created by aleks on 11.01.2017.
 */
public class Mark {
    private int studentId;
    private String surname;
    private String name;
    private String patronymic;
    private String groupName;
    private String markName;
    private String subjectName;

    public Mark() {
    }

    public Mark(String surname, String name, String patronymic, String groupName, String markName, String subjectName) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.groupName = groupName;
        this.markName = markName;
        this.subjectName = subjectName;
    }

    public String getMarkName() {
        return markName;
    }

    public void setMarkName(String markName) {
        this.markName = markName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}

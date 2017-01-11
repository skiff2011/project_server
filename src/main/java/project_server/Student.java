package project_server;

import java.util.Date;

/**
 * Created by aleks on 09.01.2017.
 */
public class Student {
    private int studentId;
    private int groupId;
    private String groupCode;
    private String surname;
    private String name;
    private String patronymic;
    private Date birthDate;
    private String sex;
    private String birthPlace;
    private String adress;
    private String telephone;
    private String bookNo;
    private String note;
    private String contractNo;
    private Date contractDate;
    private String contractKind;

    public Student() {
    }

    public Student(int studentId, String groupCode, String surname, String name, String patronymic, Date birthDate, String sex, String birthPlace, String adress, String telephone, String bookNo, String note, String contractNo, Date contractDate, String contractKind) {
        this.studentId = studentId;
        this.groupCode = groupCode;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.sex = sex;
        this.birthPlace = birthPlace;
        this.adress = adress;
        this.telephone = telephone;
        this.bookNo = bookNo;
        this.note = note;
        this.contractNo = contractNo;
        this.contractDate = contractDate;
        this.contractKind = contractKind;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBookNo() {
        return bookNo;
    }

    public void setBookNo(String bookNo) {
        this.bookNo = bookNo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    public String getContractKind() {
        return contractKind;
    }

    public void setContractKind(String contractKind) {
        this.contractKind = contractKind;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", groupCode='" + groupCode + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthDate=" + birthDate +
                ", sex='" + sex + '\'' +
                ", birthPlace='" + birthPlace + '\'' +
                ", adress='" + adress + '\'' +
                ", telephone='" + telephone + '\'' +
                ", bookNo='" + bookNo + '\'' +
                ", note='" + note + '\'' +
                ", contractNo='" + contractNo + '\'' +
                ", contractDate=" + contractDate +
                ", contractKind='" + contractKind + '\'' +
                '}';
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}

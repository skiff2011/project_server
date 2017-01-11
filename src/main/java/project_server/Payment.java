package project_server;

import java.util.Date;

/**
 * Created by aleks on 11.01.2017.
 */
public class Payment {
    private int paymentId;
    private String contractNo;
    private String surname;
    private String name;
    private String patronymic;
    private String groupCode;
    private Date paymentDate;
    private double paymentSum;
    private String documentNo;

    public Payment() {
    }

    public Payment(int paymentId, String contractNo, String surname, String name, String patronymic, String groupCode, Date paymentDate, double paymentSum, String documentNo) {
        this.paymentId = paymentId;
        this.contractNo = contractNo;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.groupCode = groupCode;
        this.paymentDate = paymentDate;
        this.paymentSum = paymentSum;
        this.documentNo = documentNo;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
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

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(double paymentSum) {
        this.paymentSum = paymentSum;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }
}

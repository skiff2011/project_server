package project_server;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by aleks on 04.01.2017.
 */
public class DBWorker {
    private static final String URL = "jdbc:mysql://localhost:3306/project_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private Connection connection;
    public DBWorker() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println(connection.isClosed());
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Cafedra> getCafedras(){
        List<Cafedra> cafedraList=new ArrayList<>();
        try {
            Statement statement=connection.createStatement();
            ResultSet set=statement.executeQuery("SELECT * FROM cafedra");
            while (set.next()){
                Cafedra cafedra=new Cafedra();
                cafedra.setId(set.getInt("idcafedra"));
                cafedra.setName(set.getString("cafedra_name"));
                cafedra.setShifr(set.getString("cafedra_shifr"));
                cafedraList.add(cafedra);
            }
        } catch (SQLException e) {
            return null;
        }
        return cafedraList;
    }

    public List<Speciality> getSpecialisations(){
        List<Speciality> specialities=new ArrayList<>();
        try {
            Statement statement=connection.createStatement();
            ResultSet set=statement.executeQuery("SELECT speciality.idspeciality,speciality.speciality_name,speciality.speciality_shifr,cafedra.cafedra_name\n" +
                    "from speciality\n" +
                    "join cafedra\n" +
                    "on speciality.cafedra_id=cafedra.idcafedra;");
            while (set.next()){
                Speciality speciality=new Speciality();
                speciality.setId(set.getInt("idspeciality"));
                speciality.setCafedra(set.getString("cafedra_name"));
                speciality.setName(set.getString("speciality_name"));
                speciality.setShifr(set.getString("speciality_shifr"));
                specialities.add(speciality);
            }
        } catch (SQLException e) {
            return null;
        }
        return specialities;
    }

    public List<Group> getGroups(){
        List<Group> groups=new ArrayList<>();
        try {
            Statement statement=connection.createStatement();
            ResultSet set=statement.executeQuery("select groups.idgroup,groups.group_code,groups.group_create_date,speciality.speciality_name from groups\n" +
                    "join speciality \n" +
                    "on groups.speciality_id=speciality.idspeciality");
            while (set.next()){
                Group group=new Group();
                group.setId(set.getInt("idgroup"));
                group.setGroupCode(set.getString("group_code"));
                group.setCreateDate(set.getDate("group_create_date"));
                group.setSpecialityName(set.getString("speciality_name"));
                groups.add(group);
            }
        } catch (SQLException e) {
            return null;
        }
        return groups;
    }

    public List<Mark> getMarks(){
        List<Mark> marks=new ArrayList<>();
        try {
            Statement statement=connection.createStatement();
            ResultSet set=statement.executeQuery("select student_marks.student_id,person.surname,person.name,person.patronymic,student_marks.subject,smark.mark_name,groups.group_code from\n" +
                    "student\n" +
                    "join person on student.person_id=person.idperson\n" +
                    "join student_marks on student_marks.student_id=student.idstudent\n" +
                    "join smark on smark.idsmark=student_marks.mark_id\n" +
                    "join student_group on student_group.student_id=student.idstudent\n" +
                    "join groups on groups.idgroup=student_group.group_id\n" +
                    "order by groups.group_code asc,student_marks.subject asc");
            while (set.next()){
                Mark mark=new Mark();
                mark.setStudentId(set.getInt("student_id"));
                mark.setSurname(set.getString("surname"));
                mark.setName(set.getString("name"));
                mark.setPatronymic(set.getString("patronymic"));
                mark.setSubjectName(set.getString("subject"));
                mark.setMarkName(set.getString("mark_name"));
                mark.setGroupName(set.getString("group_code"));
                marks.add(mark);
            }
        } catch (SQLException e) {
            return null;
        }
        return marks;
    }

    public List<Payment> getPayment(){
        List<Payment> payments=new ArrayList<>();
        try {
            Statement statement=connection.createStatement();
            ResultSet set=statement.executeQuery("select payment.idpayment, contract.contract_no, person.surname,person.name,person.patronymic,groups.group_code,\n" +
                    "payment.payment_date,payment.payment_sum,payment.document_no\n" +
                    "from payment\n" +
                    "join contract on payment.contract_id=contract.idcontract\n" +
                    "join student on contract.student_id=student.idstudent\n" +
                    "join person on student.person_id=person.idperson\n" +
                    "join student_group on student.idstudent=student_group.student_id\n" +
                    "join groups on groups.idgroup=student_group.group_id");
            while (set.next()){
                Payment payment=new Payment();
                payment.setPaymentId(set.getInt("idpayment"));
                payment.setContractNo(set.getString("contract_no"));
                payment.setSurname(set.getString("surname"));
                payment.setName(set.getString("name"));
                payment.setPatronymic(set.getString("patronymic"));
                payment.setGroupCode(set.getString("group_code"));
                payment.setPaymentDate(set.getDate("payment_date"));
                payment.setPaymentSum(set.getDouble("payment_sum"));
                payment.setDocumentNo(set.getString("document_no"));
                payments.add(payment);
            }
        } catch (SQLException e) {
            return null;
        }
        return payments;
    }

    public List<Violation> getViolation(){
        List<Violation> violations=new ArrayList<>();
        try {
            Statement statement=connection.createStatement();
            ResultSet set=statement.executeQuery("select violation.idviolation,person.surname,person.name,person.patronymic," +
                    "sviolation_kind.violation_kind_name,spunish_kind.punish_kind_name,violation.violation_date\n" +
                    "from violation\n" +
                    "join sviolation_kind on violation.violation_kind_id=sviolation_kind.idviolation_kind\n" +
                    "join spunish_kind on spunish_kind.idpunish_kind=violation.punish_kind_id\n" +
                    "join person on violation.person_id=person.idperson");
            while (set.next()){
                Violation violation=new Violation();
                violation.setId(set.getInt("idviolation"));
                violation.setSurname(set.getString("surname"));
                violation.setName(set.getString("name"));
                violation.setPatronymic(set.getString("patronymic"));
                violation.setViolationKind(set.getString("violation_kind_name"));
                violation.setPunish(set.getString("punish_kind_name"));
                violation.setDate(set.getDate("violation_date"));
                violations.add(violation);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return violations;
    }

    public List<ViolationKind> getViolationKind(){
        List<ViolationKind> violations=new ArrayList<>();
        try {
            Statement statement=connection.createStatement();
            ResultSet set=statement.executeQuery("SELECT * FROM project_db.sviolation_kind");
            while (set.next()){
                ViolationKind violationKind=new ViolationKind();
                violationKind.setId(set.getInt("idviolation_kind"));
                violationKind.setName(set.getString("violation_kind_name"));
                violations.add(violationKind);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return violations;
    }

    public List<PunishKind> getPunishKind(){
        List<PunishKind> punishKinds=new ArrayList<>();
        try {
            Statement statement=connection.createStatement();
            ResultSet set=statement.executeQuery("SELECT * FROM project_db.spunish_kind");
            while (set.next()){
                PunishKind punishKind=new PunishKind();
                punishKind.setId(set.getInt("idpunish_kind"));
                punishKind.setName(set.getString("punish_kind_name"));
                punishKinds.add(punishKind);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return punishKinds;
    }

    public List<Person> getPersons(){
        List<Person> personList=new ArrayList<>();
        try {
            Statement statement=connection.createStatement();
            ResultSet set=statement.executeQuery("SELECT * FROM project_db.person");
            while (set.next()){
                Person person=new Person();
                person.setId(set.getInt("idperson"));
                person.setSurname(set.getString("surname"));
                person.setName(set.getString("name"));
                person.setPatronymic(set.getString("patronymic"));
                personList.add(person);
            }
            return personList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Student> getStudents(){
        List<Student> students=new ArrayList<>();
        try {
            Statement statement=connection.createStatement();
            ResultSet set=statement.executeQuery("select idstudent,idgroup,group_code,surname,name,patronymic,birth_date,sex,birthplace,\n" +
                    "adress,telephone,book_no,note,contract_no,contract_date,contact_kind_name\n" +
                    "from \n" +
                    "(select * from (select * from student join person on student.person_id=person.idperson) as sp\n" +
                    "left join (select * from contract join scontract_kind on contract.contract_kind_id=scontract_kind.idcontract_kind) as ck\n" +
                    "on ck.student_id=sp.idstudent) as result\n" +
                    "join (select * from student_group join groups on student_group.group_id=groups.idgroup) as t\n" +
                    "on t.student_id=result.idstudent\n" +
                    "order by idstudent");
            while (set.next()){
                Student student=new Student();
                student.setGroupId(set.getInt("idgroup"));
                student.setStudentId(set.getInt("idstudent"));
                student.setGroupCode(set.getString("group_code"));
                student.setSurname(set.getString("surname"));
                student.setName(set.getString("name"));
                student.setPatronymic(set.getString("patronymic"));
                student.setBirthDate(set.getDate("birth_date"));
                student.setSex(set.getString("sex"));
                student.setBirthPlace(set.getString("birthplace"));
                student.setAdress(set.getString("adress"));
                student.setTelephone(set.getString("telephone"));
                student.setBookNo(set.getString("book_no"));
                student.setNote(set.getString("note"));
                student.setContractNo(set.getString("contract_no"));
                student.setContractDate(set.getDate("contract_date"));
                student.setContractKind(set.getString("contact_kind_name"));
                students.add(student);
            }
        } catch (SQLException e) {
            return null;
        }
        return students;
    }

    public String addCafedra(String name,String shifr){
        try {
            PreparedStatement statement=connection.prepareStatement("INSERT INTO cafedra (cafedra_name,cafedra_shifr) VALUES (?,?)");
            statement.setString(1,name);
            statement.setString(2,shifr);
            return String.valueOf(statement.execute());
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String addSpeciality(int cafedra,String name,String shifr){
        try {
            PreparedStatement statement=connection.prepareStatement("INSERT INTO speciality (cafedra_id,speciality_name,speciality_shifr) VALUES (?,?,?)");
            statement.setInt(1,cafedra);
            statement.setString(2,name);
            statement.setString(3,shifr);
            return String.valueOf(statement.execute());
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String addGroup(String groupName,Date createDate,int specialityId){
        try {
            PreparedStatement statement=connection.prepareStatement("INSERT INTO groups(speciality_id,group_code,group_create_date) VALUES (?,?,?)");
            statement.setInt(1,specialityId);
            statement.setString(2,groupName);
            statement.setDate(3,createDate);
            return String.valueOf(statement.execute());
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String addMark(int studentId,int markID, String subject){
        try{
            PreparedStatement statement=connection.prepareStatement("INSERT INTO student_marks VALUE (?,?,?)");
            statement.setInt(1,studentId);
            statement.setInt(2,markID);
            statement.setString(3,subject);
            return String.valueOf(statement.execute());
        } catch (SQLException e) {
           return e.getMessage();
        }
    }

    public String addPayment(int contractId, java.util.Date paymentDate,double sum,String documentNo){
        try{
            PreparedStatement statement=connection.prepareStatement("INSERT INTO payment (contract_id, payment_date, payment_sum, document_no) VALUES (?, ?, ?, ?)");
            statement.setInt(1,contractId);
            statement.setDate(2,new Date(paymentDate.getTime()));
            statement.setDouble(3,sum);
            statement.setString(4,documentNo);
            return String.valueOf(statement.execute());
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String addViolation(int violationKindId, int punishKindId, java.util.Date date, int personId){
        try{
            PreparedStatement statement=connection.prepareStatement("INSERT INTO violation (violation_kind_id, punish_kind_id, violation_date, person_id) VALUES (?, ?, ?, ?)");
            statement.setInt(1,violationKindId);
            statement.setInt(2,punishKindId);
            statement.setDate(3,new Date(date.getTime()));
            statement.setInt(4,personId);
            return String.valueOf(statement.execute());
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String addStudent(Student student){
        int personId;
        int studentId;
        try{
            connection.setAutoCommit(false);
            PreparedStatement statement=connection.prepareStatement("INSERT INTO person (surname, name, patronymic, birth_date, adress, sex, birthplace, telephone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1,student.getSurname());
            statement.setString(2,student.getName());
            statement.setString(3,student.getPatronymic());
            statement.setDate(4,new Date(student.getBirthDate().getTime()));
            statement.setString(5,student.getAdress());
            statement.setString(6,student.getSex());
            statement.setString(7,student.getBirthPlace());
            statement.setString(8,student.getTelephone());
            statement.execute();

            Statement statement1=connection.createStatement();
            ResultSet set=statement1.executeQuery("select max(person.idperson) as id from person");
            set.next();
            personId=set.getInt("id");

            statement=connection.prepareStatement("INSERT INTO student (book_no, note, person_id) VALUES (?, ?, ?);");
            statement.setString(1,student.getBookNo());
            statement.setString(2,student.getNote());
            statement.setInt(3,personId);
            statement.execute();

            statement1=connection.createStatement();
            set=statement1.executeQuery("select max(student.idstudent) as id from student");
            set.next();
            studentId=set.getInt("id");

            if(student.getContractNo()!=null) {
                statement = connection.prepareStatement("insert into contract (student_id, contract_kind_id, contract_date, contract_no,contract_sum) VALUES (?, ?, ?, ?, ?)");
                statement.setInt(1, studentId);
                statement.setInt(2, 1);
                statement.setDate(3, new Date(student.getContractDate().getTime()));
                statement.setString(4, student.getContractNo());
                statement.setDouble(5, 20000);
                statement.execute();
            }

            statement=connection.prepareStatement("INSERT INTO student_group (group_id, student_id, putting_date) VALUES (?, ?, ?)");
            statement.setInt(1,student.getGroupId());
            statement.setInt(2,studentId);
            statement.setDate(3,new Date(System.currentTimeMillis()));
            statement.execute();

            connection.commit();
            connection.setAutoCommit(true);
            return "added";
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String deleteCafedra(int id){
        try{
            PreparedStatement statement=connection.prepareStatement("DELETE FROM cafedra WHERE idcafedra=?");
            statement.setInt(1,id);
            return String.valueOf(statement.execute());
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String deleteSpeciality(int id){
        try{
            PreparedStatement statement=connection.prepareStatement("DELETE FROM speciality WHERE idspeciality=?");
            statement.setInt(1,id);
            return String.valueOf(statement.execute());
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String deleteGroup(int id){
        try{
            PreparedStatement statement=connection.prepareStatement("DELETE FROM groups WHERE idgroup=?");
            statement.setInt(1,id);
            return String.valueOf(statement.execute());
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String deleteMark(int studentId,String subject){
        try{
            PreparedStatement statement=connection.prepareStatement("delete from student_marks where student_id=? and subject=?");
            statement.setInt(1,studentId);
            statement.setString(2,subject);
            return String.valueOf(statement.execute());
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String deletePayment(int paymentId){
        try{
            PreparedStatement statement=connection.prepareStatement("delete from payment where idpayment=?");
            statement.setInt(1,paymentId);
            return String.valueOf(statement.execute());
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String deleteViolation(int violationId){
        try{
            PreparedStatement statement=connection.prepareStatement("delete from violation where idviolation=?");
            statement.setInt(1,violationId);
            return String.valueOf(statement.execute());
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String deleteStudent(int studentId){
        try{
            connection.setAutoCommit(false);
            PreparedStatement statement=connection.prepareStatement("select student.person_id,contract.idcontract from student\n" +
                    "left join contract \n" +
                    "on student.idstudent=contract.student_id\n" +
                    "where idstudent=?");
            statement.setInt(1,studentId);
            ResultSet set=statement.executeQuery();
            if(set.next()){
                System.out.println(set.getInt("person_id"));
                System.out.println(set.getInt("idcontract"));
                statement=connection.prepareStatement("delete FROM contract where idcontract=?");
                statement.setInt(1,set.getInt("idcontract"));
                statement.execute();
                statement=connection.prepareStatement("delete from student_group where student_id=?");
                statement.setInt(1,studentId);
                statement.execute();
                statement=connection.prepareStatement("delete from student where idstudent=?");
                statement.setInt(1,studentId);
                statement.execute();
                statement=connection.prepareStatement("delete from person where idperson=?");
                statement.setInt(1,set.getInt("person_id"));
                statement.execute();
                connection.commit();
                return "true";
            }
            connection.setAutoCommit(true);
            return "false";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
}

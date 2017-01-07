package project_server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
}

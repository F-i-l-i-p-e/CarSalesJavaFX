// Filipe Nogueira Santos
//200534531
// MidTerm Exam - 23S Adv Object Oriented Prog-Java - 200 COMP1011-23S-31928
package com.example.f22midterm;
import java.sql.*;
import java.util.ArrayList;
public class DBAccess {
    // get cars from DB
    public static ArrayList<Car> getAllCars() {
        ArrayList<Car> cars = new ArrayList<>();
        try {Connection conn = DBUtility.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM carSales");
            while (resultSet.next()) {
                Car car = new Car(
                        resultSet.getInt("carID"),
                        resultSet.getInt("modelYear"),
                        resultSet.getString("make"),
                        resultSet.getString("model"),
                        resultSet.getDouble("price"),
                        resultSet.getDate("dateSold").toLocalDate());
                cars.add(car);}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }
    // get unique years for sold cars
    public static ArrayList<Integer> getSoldYears() {
        ArrayList<Integer> years = new ArrayList<>();
        try {
            Connection conn = DBUtility.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT YEAR(dateSold) FROM carSales");
            while (resultSet.next()) {
                years.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return years;
    }
}

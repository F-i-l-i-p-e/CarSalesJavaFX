// Filipe Nogueira Santos
//200534531
// MidTerm Exam - 23S Adv Object Oriented Prog-Java - 200 COMP1011-23S-31928
package com.example.f22midterm;
import java.time.LocalDate;

public class Car {
    private int carID;     // Car ID field
    private int modelYear;    // Model year field
    private String make;      // Make field
    private String model;  // Model field
    private double price;    // Price field
    private LocalDate dateSold;    // Date sold field

    public Car(int carID, int modelYear, String make, String model, double price, LocalDate dateSold) {
        setCarID(carID);   // Initialize Car ID
        setModelYear(modelYear);    // Initialize model year
        setMake(make);    // Initialize make
        setModel(model); // Initialize model
        setPrice(price); // Initialize price
        setDateSold(dateSold); // Initialize date sold
    }

    public int getCarID() {
        return carID;                  // Get Car ID
    }

    public void setCarID(int carID) {
        if (carID <= 0) {
            throw new IllegalArgumentException("Car ID should be greater than 0");
        }
        this.carID = carID; // Set Car ID
    }

    public int getModelYear() {
        return modelYear;  // Get model year
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear; // Set model year
    }

    public String getMake() {
        return make;  // Get make
    }

    public void setMake(String make) {
        if (!isValidMake(make)) {
            throw new IllegalArgumentException("Invalid make. Must be 'Acura', 'Ford', 'Honda', 'Nissan', or 'Tesla'");
        }
        this.make = make; // Set maker
    }

    private boolean isValidMake(String make) {
        return make.equals("Acura") || make.equals("Ford") || make.equals("Honda") || make.equals("Nissan") || make.equals("Tesla");
    }

    public String getModel() {
        return model;  // Get model
    }

    public void setModel(String model) {
        if (model.length() < 2) {
            throw new IllegalArgumentException("Model should be at least 2 characters long");
        }
        this.model = model;// Set model
    }

    public double getPrice() {
        return price;  // Get price
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price should be greater than 0");
        }
        this.price = price;   // Set price
    }

    public LocalDate getDateSold() {
        return dateSold; // Get date sold
    }

    public void setDateSold(LocalDate dateSold) {
        if (dateSold.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("The date sold should be no later than today");
        }
        this.dateSold = dateSold;  // Set date sold
    }
}

// Filipe Nogueira Santos
//200534531
// MidTerm Exam - 23S Adv Object Oriented Prog-Java - 200 COMP1011-23S-31928
package com.example.f22midterm;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {
    @FXML
    private TableView<Car> carTable;     // Car table
    @FXML
    private TableColumn<Car, Integer> carIdColumn;    // Car ID
    @FXML
    private TableColumn<Car, Integer> modelYearColumn;  // model year
    @FXML
    private TableColumn<Car, String> makeColumn;     // maker
    @FXML
    private TableColumn<Car, String> modelColumn;  // model
    @FXML
    private TableColumn<Car, Double> priceColumn;    // price
    @FXML
    private TableColumn<Car, LocalDate> dateSoldColumn; // sold date
    @FXML
    private BarChart<String, Number> barChart;      // Bar chart
    @FXML
    private Label unitsSoldLabel;             // Units sold label
    @FXML
    private Label totalSalesLabel;            // Total sales label
    @FXML
    private ComboBox<String> yearFilterComboBox;     // Year filter combo box
    @FXML
    private HBox chartContainer;       // Chart container
    private List<Car> carList;               // list cars
    @FXML
    private void initialize() {
        carList = DBAccess.getAllCars();       // Cars from DB
        initializeTableColumns();           // Initialize table
        carTable.getItems().setAll(carList);          // Set items on car table
        carTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // resize table
        initializeYearFilterComboBox();      // filter
        updateLabelsAndChart(carList);         // label updates
        initializeChartContainer();         // chart Container
    }
    private void initializeTableColumns() {
        carIdColumn.setCellValueFactory(new PropertyValueFactory<>("carID"));  // car ID
        modelYearColumn.setCellValueFactory(new PropertyValueFactory<>("modelYear"));  // Model year
        makeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));  // Make
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));  // model
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));   // price
        dateSoldColumn.setCellValueFactory(new PropertyValueFactory<>("dateSold")); // date sold
    }
    private void initializeYearFilterComboBox() {
        yearFilterComboBox.getItems().add("Filter by Year"); // DEFAULT filter option
        yearFilterComboBox.getItems().addAll(DBAccess.getSoldYears().stream().map(String::valueOf).collect(Collectors.toList())); // add sold year FILTER
        yearFilterComboBox.getSelectionModel().selectFirst();// First option DEFAULT

        // listener for FILTER
        yearFilterComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals("Filter by Year")) {
                int selectedYear = Integer.parseInt(newValue);
                List<Car> filteredCars = carList.stream()
                        .filter(car -> getYear(car.getDateSold()) == selectedYear)
                        .collect(Collectors.toList());
                carTable.getItems().setAll(filteredCars);
                updateLabelsAndChart(filteredCars);}
        });
    }
    private void updateLabelsAndChart(List<Car> cars) {
        int unitsSold = cars.size();   // calculate units sold
        double totalSales = cars.stream().mapToDouble(Car::getPrice).sum(); // calculate total sales
        unitsSoldLabel.setText("Units sold: " + unitsSold);    // Update units sold
        totalSalesLabel.setText(String.format("Total Sales: $%.2f", totalSales)); // Update total sales
        Map<String, Integer> makeCount = new HashMap<>();
        for (Car car : cars) {
            makeCount.put(car.getMake(), makeCount.getOrDefault(car.getMake(), 0) + 1);
        }
        // Update bar chart data
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (Map.Entry<String, Integer> entry : makeCount.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        barChart.getData().setAll(series);
    }
    private int getYear(LocalDate date) {
        return date.getYear();// get year from LocalDate
    }
    private void initializeChartContainer() {
        double screenWidth = Screen.getPrimary().getBounds().getWidth(); // Get screen width
        double desiredWidth = screenWidth * 0.5;      // Set desired width
        chartContainer.setPrefWidth(desiredWidth);    // set prefered width

        // Chart container width
        Platform.runLater(() -> {
            chartContainer.widthProperty().addListener((observable, oldValue, newValue) -> {
                double updatedWidth = newValue.doubleValue() * 0.5;
                chartContainer.setPrefWidth(updatedWidth);});
        });
    }
}

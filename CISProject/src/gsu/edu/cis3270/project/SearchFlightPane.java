package gsu.edu.cis3270.project;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.beans.value.*;

public abstract class SearchFlightPane extends VBox {
	
    protected TextField txtToCity;
    protected TextField txtFromCity;
    protected TableView<Flight> table;
    MySQLAccess db = new MySQLAccess();
    public ObservableList<Flight> lstFlight;
    public GridPane grid;
 
    
    public SearchFlightPane(GUI gui) {
    	
    	init(gui);
    	
    }

    public void init(GUI gui) {
    	
    	lstFlight = FXCollections.observableArrayList(gui.searchResults);
    	
    	grid = new GridPane();
    	
        table = new TableView<Flight>();
        this.getChildren().add(table);

        TableColumn<Flight, String> colString;
        TableColumn<Flight, Integer> colInt;
        TableColumn<Flight, java.sql.Date> colDate;

        colInt = new TableColumn<Flight, Integer>("Flight");
        colInt.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        table.getColumns().add(colInt);
        
        colString = new TableColumn<Flight, String>("To City");
        colString.setCellValueFactory(new PropertyValueFactory<>("toCity"));
        table.getColumns().add(colString);

        colString = new TableColumn<Flight, String>("From City");
        colString.setCellValueFactory(new PropertyValueFactory<>("fromCity"));
        table.getColumns().add(colString);

        colDate = new TableColumn<Flight, java.sql.Date>("Departure Time");
        colDate.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        table.getColumns().add(colDate);

        colDate = new TableColumn<Flight, java.sql.Date>("Arrival Time");
        colDate.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        table.getColumns().add(colDate);

        colInt = new TableColumn<Flight, Integer>("Passengers");
        colInt.setCellValueFactory(new PropertyValueFactory<>("passengers"));
        table.getColumns().add(colInt);
        
        
        // Display row data
        table.setItems(lstFlight);
    	
    	
    	
        //TextField txt;
        //Label lbl;
        
        this.getChildren().add(grid);
        
        //grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));        
        
        int row = 0;

        grid.add(new Label("From City"), 0, row);
        txtFromCity = new TextField();
        grid.add(txtFromCity, 1, row++);
        
        grid.add(new Label("To City"), 0, row);
        txtToCity = new TextField();
        grid.add(txtToCity, 1, row++);

        
        Button cmd = new Button("Search");
        cmd.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                SearchFlightPane.this.onSearch();
            }
        });
        
        this.getChildren().add(cmd);
        
        
        Button cmdOk = new Button("Add Flight");
        cmdOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                int row = table.getSelectionModel().getSelectedIndex();
                System.out.println("Selected row => "+row);
                onFlightSelected(row);
            }
        });
        cmdOk.setDisable(true);
        this.getChildren().add(cmdOk);
        
        
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            cmdOk.setDisable(newSelection == null);
        });
        
        Button cancel = new Button("Back");
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                onCancel();
            }
        });
        this.getChildren().add(cancel);
        
        
        
    }

    public String getFromCity() {
        String s = txtFromCity.getText();
        return s;
    }
    public String getToCity() {
        String s = txtToCity.getText();
        return s;
    }
    
	public void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}
	
	public void clearScene(){
		this.getChildren().clear();
	}
    
    
    protected abstract void onSearch();
    protected abstract void onFlightSelected(int row);
    protected abstract void onCancel();

}

package gsu.edu.cis3270.project;

import gsu.edu.cis3270.project.Flight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Window;

public abstract class MyFlightsPane extends VBox {

    protected TableView<Flight> table;
    protected ObservableList<Flight> lstFlight; 
    
    public MyFlightsPane(GUI gui) { 	
    	
        init(gui);
    }

    protected void init(GUI gui) {
    	
    	lstFlight = FXCollections.observableArrayList(gui.searchResults);
    	
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

        
        Button cmdOk = new Button("Remove This Flight");
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
        
        Button cmd = new Button("Back");
        cmd.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                onCancel();
            }
        });
        this.getChildren().add(cmd);
    }
    
	public void clearScene(){
		this.getChildren().clear();
	}
	
	public void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}

    
    
    protected abstract void onFlightSelected(int row);
    protected abstract void onCancel();

}

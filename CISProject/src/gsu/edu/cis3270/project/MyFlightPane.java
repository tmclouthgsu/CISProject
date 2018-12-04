package gsu.edu.cis3270.project;

import java.util.ArrayList;

import gsu.edu.cis3270.project.Flight;
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
import javafx.beans.value.*;

public abstract class MyFlightPane extends VBox {
    protected Flight flight;
    protected Label lblFlightNumber;
    protected Label lblToCity;
    protected Label lblFromCity;
    protected Label lblDepartureTime;
    protected Label lblArrivalTime;
    protected Label lblPassengers;
    
    protected Button cmdDeleteFlight;
    protected Button cmdCancel;
    protected Button cmdGotoHome;
    
    
    public MyFlightPane() {
        init();
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
        if (flight == null) return;
        lblFlightNumber.setText(""+flight.getFlightNumber());
        lblToCity.setText(flight.getToCity());
        lblFromCity.setText(flight.getFromCity());
        lblDepartureTime.setText(""+flight.getDepartureTime());
        lblArrivalTime.setText(""+flight.getArrivalTime());
        lblPassengers.setText(""+flight.getPassengers());
    }

    
    
    protected void init() {
        TextField txt;
        Label lbl;
        
        GridPane grid = new GridPane();
        this.getChildren().add(grid);
        
        //grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));        
        
        int row = 0;
        grid.add(new Label("Flight Number"), 0, row);
        lblFlightNumber = new Label();
        grid.add(lblFlightNumber, 1, row++);
        
        grid.add(new Label("To City"), 0, row);
        lblToCity = new Label();
        grid.add(lblToCity, 1, row++);

        grid.add(new Label("From City"), 0, row);
        lblFromCity = new Label();
        grid.add(lblFromCity, 1, row++);
        
        grid.add(new Label("Departure Time"), 0, row);
        lblDepartureTime = new Label();
        grid.add(lblDepartureTime, 1, row++);
        
        grid.add(new Label("Arrival Time"), 0, row);
        lblArrivalTime = new Label();
        grid.add(lblArrivalTime, 1, row++);
        
        grid.add(new Label("Passengers"), 0, row);
        lblPassengers = new Label();
        grid.add(lblPassengers, 1, row++);
        
        
        this.getChildren().add(getDeleteFlightButton());
        this.getChildren().add(getCancelButton());
        this.getChildren().add(getGotoHomeButton());
    }

    public Button getDeleteFlightButton() {
        if (cmdDeleteFlight != null) return cmdDeleteFlight;
        cmdDeleteFlight = new Button("Delete this Flight");
        cmdDeleteFlight.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                MyFlightPane.this.onDeleteFlight();
            }
        });
        return cmdDeleteFlight;
    }
    
    public Button getCancelButton() {
        if (cmdCancel != null) return cmdCancel;
        cmdCancel = new Button("Cancel");
        cmdCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                onCancel();
            }
        });
        return cmdCancel;
    }

    public Button getGotoHomeButton() {
        if (cmdGotoHome != null) return cmdGotoHome;
        cmdGotoHome = new Button("Goto Home");
        cmdGotoHome.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                onGotoHome();
            }
        });
        return cmdGotoHome;
    }
    
    protected abstract void onDeleteFlight();
    protected abstract void onCancel();
    protected abstract void onGotoHome();

}

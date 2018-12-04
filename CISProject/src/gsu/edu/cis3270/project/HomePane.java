package gsu.edu.cis3270.project;

import gsu.edu.cis3270.project.User;
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

public abstract class HomePane extends HBox {
    protected TextField txtToCity;
    protected TextField txtFromCity;
    protected Button cmdAdmin;
    protected Button cmdMyFlights;
    protected User user;
    
    public HomePane(User user) {
        this.user = user;
        init();
    }
    
    protected void init() {
        TextField txt;
        Label lbl;
        
        GridPane grid = new GridPane();
        
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
                HomePane.this.onSearch();
            }
        });
        
        VBox vbox = new VBox();
        vbox.getChildren().add(grid);
        vbox.getChildren().add(cmd);
        this.getChildren().add(vbox);
        
      
        
        HBox hbox = new HBox();

        cmdAdmin = new Button("Admin");
        cmdAdmin.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                HomePane.this.onAdmin();
            }
        });
        cmdAdmin.setVisible(user != null && user.getIsAdmin() > 0);
        hbox.getChildren().add(cmdAdmin);
        
        cmdMyFlights = new Button("My Flights");
        cmdMyFlights.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                HomePane.this.onMyFlights();
            }
        });
        hbox.getChildren().add(cmdMyFlights);
        this.getChildren().add(hbox);
    }

    public String getFromCity() {
        String s = txtFromCity.getText();
        return s;
    }
    public String getToCity() {
        String s = txtToCity.getText();
        return s;
    }
    
    
    protected abstract void onSearch();
    protected abstract void onAdmin();
    protected abstract void onMyFlights();
}

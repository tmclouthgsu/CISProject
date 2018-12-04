package gsu.edu.cis3270.project;

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

public abstract class SearchFlightPane extends VBox {
    protected TextField txtToCity;
    protected TextField txtFromCity;
    
    public SearchFlightPane() {
        init();
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

}

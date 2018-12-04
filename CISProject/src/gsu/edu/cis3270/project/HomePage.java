package gsu.edu.cis3270.project;

import gsu.edu.cis3270.project.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.beans.value.*;

public abstract class HomePage extends HBox {
	
	GridPane grid;
	
	public HomePage(GUI gui){
		init(gui);
	}

	public void init(GUI gui){
		grid = new GridPane();
		
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(-110);
		grid.setVgap(0);
		grid.setPadding(new Insets(25, 25, 25, 25));


		Text tltWelcome = new Text("WELCOME");
		tltWelcome.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(tltWelcome, 0, 0, 2, 1);

		Button butSearch = new Button("Search Flights");
		butSearch.setPrefWidth(200);
		HBox hbutSearch = new HBox(10);
		hbutSearch.setAlignment(Pos.CENTER_LEFT);
		hbutSearch.getChildren().add(butSearch);
		grid.add(hbutSearch, 0, 4);
		
		butSearch.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	            HomePage.this.onGoToSearch();
	        }
	    });

		Button butMyFLights = new Button("My Flights");
		butMyFLights.setPrefWidth(90);
		HBox hbutMyFLights = new HBox(20);
		hbutMyFLights.setAlignment(Pos.CENTER_LEFT);
		hbutMyFLights.getChildren().add(butMyFLights);
		grid.add(hbutMyFLights, 0, 5);
		
		butMyFLights.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	            HomePage.this.onGoToMyFlights();
	        }
	    });

		Button butAddFlight = new Button("Add New Flight");
		HBox hbutAddFlight = new HBox(0);
		hbutAddFlight.setAlignment(Pos.CENTER_LEFT);
		hbutAddFlight.getChildren().add(butAddFlight);
		grid.add(hbutAddFlight, 1, 5);
		
		butAddFlight.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	            HomePage.this.onGoToAddFlights();
	        }
	    });
		
		HBox hbox = new HBox();
		hbox.getChildren().add(grid);
		this.getChildren().add(hbox);
		
	}
	
	protected abstract void onGoToSearch();
	protected abstract void onGoToMyFlights();
	protected abstract void onGoToAddFlights();
}
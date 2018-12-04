package gsu.edu.cis3270.project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Window;
import javafx.scene.control.TextField;

// For admin to insertFlightToDB()

public abstract class AddFlightPane extends HBox {
	
	GridPane gridPane;
	
	public AddFlightPane(GUI gui){
		
		init(gui);
		
	}
	
	public void init(GUI gui){
		
		gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setPadding(new Insets(40, 40, 40, 40));
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
		columnOneConstraints.setHalignment(HPos.RIGHT);
		ColumnConstraints columnTwoConstrains = new ColumnConstraints(200, 200, Double.MAX_VALUE);
		columnTwoConstrains.setHgrow(Priority.ALWAYS);

		gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);
		
		Label headerLabel = new Label("Add Flight");
		headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		gridPane.add(headerLabel, 0, 0, 2, 1);
		GridPane.setHalignment(headerLabel, HPos.CENTER);
		GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

		// Add FIRST Name Label
		Label lblToCity = new Label("To City : ");
		gridPane.add(lblToCity, 0, 1);

		// Add FIRST Name Text Field
		TextField txtToCity = new TextField();
		txtToCity.setPrefHeight(40);
		gridPane.add(txtToCity, 1, 1);

		// Add From City Label
		Label lblFromCity = new Label("From City : ");
		gridPane.add(lblFromCity, 0, 2);

		// Add From City Text Field
		TextField txtFromCity = new TextField();
		txtFromCity.setPrefHeight(40);
		gridPane.add(txtFromCity, 1, 2);

		// Add DepartureTime Label
		Label lblDepartureTime = new Label("Depart Time : ");
		gridPane.add(lblDepartureTime, 0, 3);

		// Add DepartureTime Text Field
		DateTimePicker departureTime = new DateTimePicker();
		departureTime.setPrefHeight(40);
		gridPane.add(departureTime, 1, 3);

		// Add ArrivalTime Label
		Label lblArrivalTime = new Label("Arrival Time : ");
		gridPane.add(lblArrivalTime, 0, 4);

		// Add ArrivalTime Text Field
		

		DateTimePicker arrivalTime = new DateTimePicker();
		arrivalTime.setPrefHeight(40);
		gridPane.add(arrivalTime, 1, 4);
		//txtArrivalTime.setPromptText("In Military Time (ex. 14:20) ");

		// Add Passengers Label
		Label lblPassengers = new Label("Passengers : ");
		gridPane.add(lblPassengers, 0, 5);

		// Add Passengers Text Field
		TextField txtPassengers = new TextField();
		txtPassengers.setPrefHeight(40);
		gridPane.add(txtPassengers, 1, 5);

		// Button to add flight
		// Use insertFlightToDB()
		Button butAddFlight = new Button("Add Flight");
		butAddFlight.setPrefHeight(40);
		butAddFlight.setDefaultButton(true);
		butAddFlight.setPrefWidth(100);
		gridPane.add(butAddFlight, 0, 11, 2, 1);
		GridPane.setHalignment(butAddFlight, HPos.CENTER);
		GridPane.setMargin(butAddFlight, new Insets(0, 0, 0, 0));
		
		Button butGoToHome = new Button("Back");
		butGoToHome.setPrefHeight(40);
		butGoToHome.setDefaultButton(true);
		butGoToHome.setPrefWidth(300);
		gridPane.add(butGoToHome, 0, 12, 2, 1);
		GridPane.setHalignment(butGoToHome, HPos.CENTER);
		GridPane.setMargin(butGoToHome, new Insets(0, 0, 0, 0));
		
		butGoToHome.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				AddFlightPane.this.onBackButton();
				
			}
		});

		butAddFlight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				if (txtToCity.getText().isEmpty()) {
					showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
							"Please enter to city");
					return;
				}

				if (txtFromCity.getText().isEmpty()) {
					showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
							"Please enter from city ");
					return;
				}

				if (departureTime.getValue().toString().isEmpty()) {
					showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
							"Please pick a Departure Time");
					return;
				}

				if (arrivalTime.getValue().toString().isEmpty()) {
					showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
							"Please pick an Arrival Time");
					return;
				}

				if (txtPassengers.getText().isEmpty()) {
					showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
							"Please enter your State");
					return;
				}

				showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Confirmation",
						"You Added a Flight From " + txtFromCity.getText() + " to " + txtToCity.getText());
				
				Flight flight = new Flight((int)(Math.random()*10000000), txtToCity.getText(), txtFromCity.getText(), 
						java.sql.Timestamp.valueOf(departureTime.getDateTimeValue()), java.sql.Timestamp.valueOf(arrivalTime.getDateTimeValue()), 
						Integer.parseInt(txtPassengers.getText()));
							
				AddFlightPane.this.onAddFlightButton(flight);
				
			}
		});
		
		HBox hbox = new HBox();
		hbox.getChildren().add(gridPane);
		this.getChildren().add(hbox);
		
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
	
	protected abstract void onBackButton();
	protected abstract void onAddFlightButton(Flight flight);
}

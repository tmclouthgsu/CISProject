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
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.beans.value.*;

public abstract class LoginPage extends HBox{

	public Button butLogin = new Button("Login");
	public Button butRegister = new Button("Sign Up");
	public TextField txtUserId = new TextField();
	public PasswordField ptxtPassword = new PasswordField();
	public GridPane gridPane = new GridPane();

	public LoginPage(){
		init();
	}

	public void init(){
		
		
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setPadding(new Insets(40, 40, 40, 40));
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		ColumnConstraints columnOneConstraints = new ColumnConstraints(200, 100, Double.MAX_VALUE);
		columnOneConstraints.setHalignment(HPos.RIGHT);
		ColumnConstraints columnTwoConstrains = new ColumnConstraints(200, 200, Double.MAX_VALUE);
		columnTwoConstrains.setHgrow(Priority.ALWAYS);

		gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);
		
		// Add Header
		Label headerLabel = new Label("Sign In");
		headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		gridPane.add(headerLabel, 0, 0, 2, 1);
		GridPane.setHalignment(headerLabel, HPos.CENTER);
		GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));
		

		// Add FIRST Name Label
		Label lblUserId = new Label("Email ");
		gridPane.add(lblUserId, 0, 1);

		// Add FIRST Name Text Field
		txtUserId.setPrefHeight(40);
		gridPane.add(txtUserId, 1, 1);
		
		// Add PASSWORD Label
		Label passwordLabel = new Label("Password ");
		gridPane.add(passwordLabel, 0, 2);

		// Add PASSWORD Field
		ptxtPassword.setPrefHeight(40);
		gridPane.add(ptxtPassword, 1, 2);
		
		butRegister.setPrefHeight(40);
		butRegister.setDefaultButton(true);
		butRegister.setPrefWidth(100);
		gridPane.add(butRegister, 0, 11, 2, 1);
		GridPane.setHalignment(butRegister, HPos.RIGHT);
		GridPane.setMargin(butRegister, new Insets(20, 0, 20, 0));
		
		butLogin.setPrefHeight(40);
		butLogin.setDefaultButton(true);
		butLogin.setPrefWidth(100);
		gridPane.add(butLogin, 0, 11, 2, 1);
		GridPane.setHalignment(butLogin, HPos.LEFT);
		GridPane.setMargin(butLogin, new Insets(20, 0, 20, 0));
		
		
		butRegister.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	            LoginPage.this.onRegister();
	        }
	    });
		
		butLogin.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	            LoginPage.this.onLogin();
	        }
	    });
		
		HBox hbox = new HBox();
		hbox.getChildren().add(gridPane);
		this.getChildren().add(hbox);
	}
	
	protected abstract void onRegister();
	protected abstract void onLogin();
	
	public void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}
	


}

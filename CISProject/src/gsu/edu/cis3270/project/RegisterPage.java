package gsu.edu.cis3270.project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.control.TextField;

public abstract class RegisterPage extends HBox {
	
	public GridPane gridPane;
	
	public RegisterPage(){
		init();
	}
	
	public void init(){
		
		gridPane = new GridPane();
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
		Label headerLabel = new Label("Registration Form");
		headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		gridPane.add(headerLabel, 0, 0, 2, 1);
		GridPane.setHalignment(headerLabel, HPos.CENTER);
		GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

		// Add FIRST Name Label
		Label lblFirstName = new Label("First Name : ");
		gridPane.add(lblFirstName, 0, 1);

		// Add FIRST Name Text Field
		TextField txtFirstName = new TextField();
		txtFirstName.setPrefHeight(40);
		gridPane.add(txtFirstName, 1, 1);

		// Add LAST Name Label
		Label lblLastName = new Label("Last Name : ");
		gridPane.add(lblLastName, 0, 2);

		// Add LAST Name Text Field
		TextField txtLastName = new TextField();
		txtLastName.setPrefHeight(40);
		gridPane.add(txtLastName, 1, 2);

		// Add ADDRESS Label
		Label lblAddress = new Label("Address : ");
		gridPane.add(lblAddress, 0, 3);

		// Add ADDRESS Text Field
		TextField txtAddress = new TextField();
		txtAddress.setPrefHeight(40);
		gridPane.add(txtAddress, 1, 3);

		// Add ZIP Label
		Label lblZip = new Label("ZIP : ");
		gridPane.add(lblZip, 0, 4);

		// Add ZIP Text Field
		TextField txtZip = new TextField();
		txtZip.setPrefHeight(40);
		gridPane.add(txtZip, 1, 4);

		// Add STATE Label
		Label lblState = new Label("State : ");
		gridPane.add(lblState, 0, 5);

		// Add STATE Text Field
		TextField txtState = new TextField();
		txtState.setPrefHeight(40);
		gridPane.add(txtState, 1, 5);

		// Add EMAIL Label
		Label lblEmail = new Label("Username : ");
		gridPane.add(lblEmail, 0, 6);

		// Add USERNAME Text Field
		TextField txtUsername = new TextField();
		txtUsername.setPrefHeight(40);
		gridPane.add(txtUsername, 1, 6);

		// Add PASSWORD Label
		Label passwordLabel = new Label("Password : ");
		gridPane.add(passwordLabel, 0, 7);

		// Add PASSWORD Field
		PasswordField txtPassword = new PasswordField();
		txtPassword.setPrefHeight(40);
		gridPane.add(txtPassword, 1, 7);

		// Add SSN Label
		Label lblSSN = new Label("SSN : ");
		gridPane.add(lblSSN, 0, 8);

		// Add SSN Text Field
		PasswordField txtSSN = new PasswordField();
		txtSSN.setPrefHeight(40);
		gridPane.add(txtSSN, 1, 8);

		// Add SECURITY QUESTION Label
		Label lblSecurityQuestion = new Label("Security Question : ");
		gridPane.add(lblSecurityQuestion, 0, 9);

		// Add SECURITY QUESTION Text Field
		TextField txtSercuirtyQuestion = new TextField();
		txtSercuirtyQuestion.setPromptText("What city were you born in? ");
		txtSercuirtyQuestion.setPrefHeight(40);
		gridPane.add(txtSercuirtyQuestion, 1, 9);
		
		// Add SECURITY QUESTION Label
		Label lblSecurityQuestionAnswer = new Label("Security Question Answer : ");
		gridPane.add(lblSecurityQuestionAnswer, 0, 10);

		// Add SECURITY QUESTION Text Field
		TextField txtSercuirtyQuestionAnswer = new TextField();
		txtSercuirtyQuestion.setPrefHeight(40);
		gridPane.add(txtSercuirtyQuestionAnswer, 1, 10);
		
		
        Button cancel = new Button("Back");
		cancel.setPrefHeight(40);
		cancel.setDefaultButton(true);
		cancel.setPrefWidth(100);
		gridPane.add(cancel, 0, 11, 2, 1);
		GridPane.setHalignment(cancel, HPos.RIGHT);
		GridPane.setMargin(cancel, new Insets(20, 0, 20, 0));
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                onCancel();
            }
        });		
		
		// Button to call new user object
		Button butRegister = new Button("Register");
		butRegister.setPrefHeight(40);
		butRegister.setDefaultButton(true);
		butRegister.setPrefWidth(100);
		gridPane.add(butRegister, 0, 11, 2, 1);
		GridPane.setHalignment(butRegister, HPos.CENTER);
		GridPane.setMargin(butRegister, new Insets(20, 0, 20, 0));
		
		butRegister.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				if (txtFirstName.getText().isEmpty()) {
					showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
							"Please enter your First name");
					return;
				}

				if (txtLastName.getText().isEmpty()) {
					showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
							"Please enter your Last name");
					return;
				}

				if (txtAddress.getText().isEmpty()) {
					showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
							"Please enter your Address");
					return;
				}

				if (txtZip.getText().isEmpty()) {
					showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
							"Please enter your zip code");
					return;
				}

				if (txtState.getText().isEmpty()) {
					showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
							"Please enter your State");
					return;
				}

				if (txtUsername.getText().isEmpty()) {
					showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
							"Please enter a Username");
					return;
				}

				if (txtPassword.getText().isEmpty()) {
					showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
							"Please enter a password");
					return;
				}

				if (txtSSN.getText().isEmpty()) {
					showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
							"Please enter your Social Security Number");
					return;
				}

				if (txtSercuirtyQuestion.getText().isEmpty()) {
					showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
							"Please enter answer to the Secuity Question");
					return;
				}
				
				if (txtSercuirtyQuestionAnswer.getText().isEmpty()) {
					showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!",
							"Please enter your Email");
					return;
				}
				
				User.registerUser(txtFirstName.getText(), txtLastName.getText(), txtAddress.getText(), Integer.parseInt(txtZip.getText()), txtState.getText(), txtPassword.getText(),
						txtUsername.getText(), Integer.parseInt(txtSSN.getText()), txtSercuirtyQuestion.getText(), txtSercuirtyQuestionAnswer.getText());
				
				showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Registration Successful!",
						"Welcome " + txtUsername.getText());
				
				RegisterPage.this.onRegister();
			}
		}
		);
		
		HBox hbox = new HBox();
		hbox.getChildren().add(gridPane);
		this.getChildren().add(hbox);
		
	}
	
	public void clearScene(){
		this.getChildren().clear();
	}
	
	protected abstract void onRegister();
	protected abstract void onCancel();

	public void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}

}

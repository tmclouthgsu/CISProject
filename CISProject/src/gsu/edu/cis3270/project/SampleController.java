package gsu.edu.cis3270.project;

import javafx.scene.control.*;

public class SampleController {

	public Button butLogin;
	public Button butRegister;
	public TextField txtUserId;
	public PasswordField ptxtPassword;
	public Label lblStatus;

	public void loginButtonClick() {
		
		MySQLAccess login = new MySQLAccess();		

		try {
			if(login.getUserFromDB(txtUserId.getText()).matchPassword(ptxtPassword.getText())){
				lblStatus.setText("Login Passed. " );
				//load home page

			}
			else{
				lblStatus.setText("Login Failed. Try again...");
			}
			
		} catch (Exception e) {
		}

	}

	public void registerButtonClick() {
		lblStatus.setText("Load Register Page");
		//load register user page

	}

}

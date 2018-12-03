package gsu.edu.cis3270.project;

import javafx.scene.control.*;

public class SampleController {

	public Button butLogin;
	public Button butRegister;
	public TextField txtUserId;
	public PasswordField ptxtPassword;
	public Label lblStatus;

	public void loginButtonClick() {
		System.out.println("Loading Login...");

		String userId = txtUserId.getText();
		String password = ptxtPassword.getText();

		try {
			sa.getUserFromDB(userId);
			lblStatus.setText("");
		} catch (Exception e) {
			lblStatus.setText("Login Failed. Try again...");
		}

	}

	public void registerButtonClick() {
		System.out.println("Loading Register...");

	}

}

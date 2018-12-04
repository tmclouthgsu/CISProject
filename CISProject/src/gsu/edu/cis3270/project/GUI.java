package gsu.edu.cis3270.project;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.*;
import javafx.fxml.FXMLLoader;



public class GUI extends Application {
	
	
	private Stage stage = new Stage();

	public final int width = 1000;
	public final int height = 1000;
	
	// Model ==================
	protected User user;
	protected ObservableList<Flight> lstSearchFlight; // used by ListFlight
														// scene
	protected Flight flightSelected; // used by Flight scene
	protected Flight myFlightSelected; // user flight

	protected ObservableList<Flight> lstMyFlight; // used by MyFlight scene
	
	// View ===================
	protected Scene sceneLogin;
	protected LoginPage loginpage;
	
	protected Scene sceneRegister;
	protected RegisterPage registerpage;
	
	protected Scene sceneHome;
	protected HomePane paneHome;

	protected Scene sceneSearchFlight;
	protected SearchFlightPane paneSearchFlight;

	protected Scene sceneListFlight;
	protected ListFlightPane paneListFlight;

	protected Scene sceneBookFlight;
	protected BookFlightPane paneFlight;

	protected Scene sceneMyFlights;
	protected MyFlightsPane paneMyFlights;

	protected Scene sceneMyFlight;
	protected MyFlightPane paneMyFlight;
	
/////////////////////////////////////////////////////////////////////////////////////	
	
	
	public Scene getRegisterScene() {
		if (sceneRegister == null) {
			sceneRegister = new Scene(getRegisterPane(), width, height);
		}
		return sceneRegister;
	}
	
	public RegisterPage getRegisterPane() {
		if (registerpage != null)
			return registerpage;;
		registerpage = new RegisterPage(){
			@Override
			protected void onRegister() {
				onRegisterButtonClick();
			}
		};
		return registerpage;
	}
	
	protected void onRegisterButtonClick(){
		System.out.println("we made it to registering a user");
	}
	
	
////////////////////////////////////////////////////////////////////////////////////	
	
	
	public Scene getLoginScene() {
		if (sceneLogin == null) {
			sceneLogin = new Scene(getLoginPane(), width, height);
		}
		return sceneLogin;
	}
	
	public LoginPage getLoginPane() {
		if (loginpage != null)
			return loginpage;
		loginpage = new LoginPage(){
			@Override
			protected void onLogin() {
				onLoginButtonClick(loginpage.txtUserId.getText(),loginpage.ptxtPassword.getText());
			}
			@Override
			protected void onRegister() {
				onRegisterUserButtonClick();
			}
		};
		return loginpage;
	}
	
	protected void onRegisterUserButtonClick(){
		stage.setScene(getRegisterScene());
	}
	
	protected void onLoginButtonClick(String username, String password){
		
		MySQLAccess login = new MySQLAccess();
		
		try {
			
			User tempuser = login.getUserFromDB(username);	
			if(tempuser.matchPassword(password)){
				System.out.println("login passed");
				user = login.getUserFromDB(username);
				//go to home screen
				stage.setScene(getMyFlightsScene());
			}
			else{
				loginpage.showAlert(Alert.AlertType.ERROR, loginpage.gridPane.getScene().getWindow(), "Credential Error!",
							"The username and password do not match");
			}
			
		} catch (Exception e) {			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//////////////////////////////////////////////////////////////////////////////////
	
	public Scene getMyFlightsScene() {
		if (sceneMyFlights == null) {
			sceneMyFlights = new Scene(getMyFlightsPane(), width, height);
		}
		return sceneMyFlights;
	}

	public MyFlightsPane getMyFlightsPane() {
		if (paneMyFlights != null)
			return paneMyFlights;
		paneMyFlights = new MyFlightsPane(user) {
			@Override
			protected void onFlightSelected(int row) {
			}

			@Override
			protected void onCancel() {
				//go to home screen
			}
		};
		return paneMyFlights;
	}
	
/////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		stage.setTitle("Book-A-Flight");

		stage.setScene(getLoginScene());
		stage.show();
	}
	
	

	public static void main(String[] args) {
		launch(args);
	}
}
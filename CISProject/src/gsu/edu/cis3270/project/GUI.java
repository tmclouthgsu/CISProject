package gsu.edu.cis3270.project;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.*;
import javafx.fxml.FXMLLoader;



public class GUI extends Application {
	
	
	private Stage stage = new Stage();
	
	private MySQLAccess db = new MySQLAccess();

	public final int width = 1000;
	public final int height = 1000;
	public ArrayList<Flight> searchResults = db.getAllFLights();
	
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
	protected HomePage paneHome;

	protected Scene sceneSearchFlight;
	protected SearchFlightPane paneSearchFlight;

	protected Scene sceneMyFlights;
	protected MyFlightsPane paneMyFlights;
	
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
				stage.setScene(getHomeScene());
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
	
	public Scene getHomeScene() {
		if (sceneHome == null) {
			sceneHome = new Scene(getHomePane(), width, height);
		}
		return sceneHome;
	}
	
	public HomePage getHomePane() {
		if (paneHome != null)
			return paneHome;
		paneHome = new HomePage(this){
			@Override
			protected void onGoToMyFlights() {
				userFlights();
			}
			
			protected void onGoToSearch() {
				allFlights();
			}
			
			protected void onGoToAddFlights(){
				//onRegisterButtonClick();
			}
			
		};
		return paneHome;
	}
	
//////////////////////////////////////////////////////////////////////////////////
	
	
	public Scene getMyFlightsScene() {
		
		searchResults = db.getFlightsForUser(user);
		
		if (sceneMyFlights == null) {
			sceneMyFlights = new Scene(getMyFlightsPane(), width, height);
		}
		return sceneMyFlights;
	}

	public MyFlightsPane getMyFlightsPane() {
		if (paneMyFlights != null)
			return paneMyFlights;
		paneMyFlights = new MyFlightsPane(this) {
			@Override
			protected void onFlightSelected(int row) {
				onRemoveFlightButtonClick(row);
			}

			@Override
			protected void onCancel() {
				onCancelButtonClick();
			}
		};
		return paneMyFlights;
	}
	
	protected void onRemoveFlightButtonClick(int row){

		try {
			db.removeUserFromFlight(searchResults.get(row), user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		paneMyFlights.showAlert(Alert.AlertType.ERROR, paneMyFlights.table.getScene().getWindow(), "Remove Flight Complete",
				"You are now longer on the selected flight");
		
		paneMyFlights.clearScene();
		userFlights();
		paneMyFlights.init(this);
		stage.setScene(getMyFlightsScene());
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
	
	
	public Scene getSearchFlightScene() {
		
		if (sceneSearchFlight == null) {
			sceneSearchFlight = new Scene(getSearchFlightPane(), width, height);
		}
		return sceneSearchFlight;
	}
	
	public SearchFlightPane getSearchFlightPane() {
		if (paneSearchFlight != null)
			return paneSearchFlight;
		paneSearchFlight = new SearchFlightPane(this) {
			@Override
			protected void onFlightSelected(int row) {
				onAddFlightButtonClick(row);
			}
			
			@Override
			protected void onSearch() {
				onSearchButtonClick();
			}
			
			@Override
			protected void onCancel() {
				onCancelButtonClick();
			}
		};
		return paneSearchFlight;
	}
	
	protected void onSearchButtonClick(){
		
		if((paneSearchFlight.getToCity().isEmpty()) && (paneSearchFlight.getFromCity().isEmpty())){
			paneSearchFlight.showAlert(Alert.AlertType.ERROR, paneSearchFlight.grid.getScene().getWindow(), "Search Error!",
							"Please enter some search criteria.");
			return;
		}
		
		if((!paneSearchFlight.getToCity().isEmpty()) && (!paneSearchFlight.getFromCity().isEmpty())){
			paneSearchFlight.showAlert(Alert.AlertType.ERROR, paneSearchFlight.grid.getScene().getWindow(), "Search Error!",
							"You cannot search by both criteria at the same time.");
			return;
		}
		
		if(!paneSearchFlight.getToCity().isEmpty()){
			
			try {
				this.searchResults = db.searchByToCity(paneSearchFlight.getToCity());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			paneSearchFlight.clearScene();
			paneSearchFlight.init(this);
			stage.setScene(getSearchFlightScene());
			
		}
		
		if(!paneSearchFlight.getFromCity().isEmpty()){
			
			try {
				this.searchResults = db.searchByFromCity(paneSearchFlight.getFromCity());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			paneSearchFlight.clearScene();
			paneSearchFlight.init(this);
			stage.setScene(getSearchFlightScene());
			
		}

	}
	
	protected void onAddFlightButtonClick(int row){
		
		int result = 0;
		try {
			result = db.addUserToFlight(searchResults.get(row), user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(result == 1){
			paneSearchFlight.showAlert(Alert.AlertType.ERROR, paneSearchFlight.grid.getScene().getWindow(), "Registration Complete!",
					"Congratulations you are registered for the flight.");
					return;
		}
		else{
			paneSearchFlight.showAlert(Alert.AlertType.ERROR, paneSearchFlight.grid.getScene().getWindow(), "Registration Error!",
					"We could not register you for this flight.");
		}

		
	}
	
	
///////////////////////////////////////////////////////////////////////////////////
	
	
	public void onCancelButtonClick(){
		stage.setScene(getHomeScene());
	}
	
	public void userFlights(){
		stage.setScene(getMyFlightsScene());
		paneMyFlights.clearScene();
		searchResults = db.getFlightsForUser(user);
		paneMyFlights.init(this);
		stage.setScene(getMyFlightsScene());
		
	}
	
	public void allFlights(){
		stage.setScene(getSearchFlightScene());
		paneSearchFlight.clearScene();
		searchResults = db.getAllFLights();
		paneSearchFlight.init(this);
		stage.setScene(getSearchFlightScene());
	}
	
	
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
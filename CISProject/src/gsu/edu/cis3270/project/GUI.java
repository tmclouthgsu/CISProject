package gsu.edu.cis3270.project;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;


public class GUI extends Application {
	
	
	private Stage stage = new Stage();
	private MySQLAccess db = new MySQLAccess();
	public final int width = 700;
	public final int height = 600;
	
	// Model ==================
	protected User user;
	public ArrayList<Flight> searchResults = db.getAllFLights();
	
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
	
	protected Scene sceneAddFlight;
	protected AddFlightPane paneAddFlight;
	
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
			protected void onRegister(User user) {
				onRegisterButtonClick(user);
			}
			protected void onCancel() {
				stage.setScene(getLoginScene());
			}
		};
		return registerpage;
	}
	
	protected void onRegisterButtonClick(User user){
		this.user = user;
		stage.setScene(getHomeScene());
	}
	
	
////////////////////////////////////////////////////////////////////////////////////	
	
	
	public Scene getAddFlightScene() {
		if (sceneAddFlight == null) {
			sceneAddFlight = new Scene(getAddFlightPane(), width, height);
		}
		return sceneAddFlight;
	}
	
	public AddFlightPane getAddFlightPane() {
		if (paneAddFlight != null)
			return paneAddFlight;
		paneAddFlight = new AddFlightPane(this){
			@Override
			protected  void onBackButton()	{
				onCancelButtonClick();
			}
			protected void onAddFlightButton(Flight flight) {
				onAddFlightButtonClick(flight);
			}
		};
		return paneAddFlight;
	}
	
	protected void onAddFlightButtonClick(Flight flight){
		try {
			db.insertFlightToDB(flight);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		paneAddFlight.clearScene();
		paneAddFlight.init(this);
		stage.setScene(getAddFlightScene());
		
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////	
	
	
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
				if(user.getIsAdmin() == 1){
				stage.setScene(getAddFlightScene());
				}
				else{
					paneHome.showAlert(Alert.AlertType.ERROR, loginpage.gridPane.getScene().getWindow(), "Credential Error!",
							"This feature is only availble to Administrators");
				}
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
		
		paneMyFlights.showAlert(Alert.AlertType.CONFIRMATION, paneMyFlights.table.getScene().getWindow(), "Remove Flight Complete",
				"You are no longer on the selected flight");
		
		paneMyFlights.clearScene();
		searchResults = db.getFlightsForUser(user);
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
			paneSearchFlight.showAlert(Alert.AlertType.CONFIRMATION, paneSearchFlight.grid.getScene().getWindow(), "Registration Complete!",
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
	
	public void addFlights(){
		
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
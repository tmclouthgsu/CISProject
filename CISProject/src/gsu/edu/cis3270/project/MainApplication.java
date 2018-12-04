package gsu.edu.cis3270.project;

import java.util.ArrayList;
import java.util.Date;

import gsu.edu.cis3270.project.*;
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

public class MainApplication extends Application {

	private Stage stage;

	public final int width = 400;
	public final int height = 400;

	protected MySQLAccess mysqlAccess;

	// Model ==================
	protected User user;
	protected ObservableList<Flight> lstSearchFlight; // used by ListFlight
														// scene
	protected Flight flightSelected; // used by Flight scene
	protected Flight myFlightSelected; // user flight

	protected ObservableList<Flight> lstMyFlight; // used by MyFlight scene

	// View ===================
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

	public MainApplication() {
		mysqlAccess = new MySQLAccess();
		// qqqqq needs to get login user
		user = new User();
		user.setIsAdmin(1);
	}

	public Scene getHomeScene() {
		if (sceneHome == null) {
			sceneHome = new Scene(getHomePane(), width, height);
		}
		return sceneHome;
	}

	public HomePane getHomePane() {
		if (paneHome != null)
			return paneHome;
		paneHome = new HomePane(user) {
			@Override
			protected void onSearch() {
				onSearchFlight();
			}

			@Override
			protected void onAdmin() {
				// qqqqqqqq
				// stage.setScene(getAdminScene());
			}

			@Override
			protected void onMyFlights() {
				//stage.setScene(getMyFlightsScene());
			}
		};
		return paneHome;
	}

	/**
	 * This is used to display the list of flight from a search.
	 */
	public ObservableList<Flight> getSearchFlightList() {
		if (lstSearchFlight == null) {
			lstSearchFlight = FXCollections.observableArrayList();
		}
		return lstSearchFlight;
	}

	protected void onSearchFlight() {
		getSearchFlightList().clear();
		ArrayList<Flight> lst = null;
		try {
			String sFrom = getHomePane().getFromCity();
			String sTo = getHomePane().getFromCity();
			// qqqqqqqqqq need to have search also include toCity
			lst = mysqlAccess.searchByFromCity(sFrom);
			for (Flight f : lst) {
				// qqqqq see if toCity matches
				getSearchFlightList().add(f);
			}
		} catch (Exception e) {
			// qqqqqqqqqqqqqqqq use Dummy data
			for (int i = 0; i < 10; i++) {
				Flight f = new Flight();
				f.setFlightNumber(((int) (Math.random() * 1200)) + 200);
				f.setFromCity("ATL");
				f.setToCity("DAL");
				f.setPassengers((int) (Math.random() * 80));
				getSearchFlightList().add(f);
			}
		}
		stage.setScene(getListFlightScene());
	}

	/**
	 * List of Flights
	 */
	public Scene getListFlightScene() {
		if (sceneListFlight == null) {
			sceneListFlight = new Scene(getListFlightPane(), width, height);
		}
		return sceneListFlight;
	}

	public ListFlightPane getListFlightPane() {
		if (paneListFlight != null)
			return paneListFlight;
		paneListFlight = new ListFlightPane(getSearchFlightList()) {
			@Override
			protected void onFlightSelected(int row) {
				Flight flight = getSearchFlightList().get(row);
				MainApplication.this.onFlightSelected(flight);
			}

			@Override
			protected void onCancel() {
				MainApplication.this.onGotoHome();
			}
		};
		return paneListFlight;
	}

	/**
	 * Book the selected Flight
	 */
	public Scene getBookFlightScene() {
		if (sceneBookFlight == null) {
			sceneBookFlight = new Scene(getBookFlightPane(), width, height);
		}
		return sceneBookFlight;
	}

	public BookFlightPane getBookFlightPane() {
		if (paneFlight != null)
			return paneFlight;

		paneFlight = new BookFlightPane() {
			@Override
			protected void onBookFlight() {
				MainApplication.this.onBookFlight();
			}

			@Override
			protected void onCancel() {
				MainApplication.this.onFlightCancel();
			}

			@Override
			protected void onGotoHome() {
				MainApplication.this.onGotoHome();
			}
		};
		return paneFlight;
	}

	protected void onFlightSelected(Flight flight) {
		this.flightSelected = flight;
		getBookFlightPane().setFlight(flight);
		stage.setScene(getBookFlightScene());
	}

	protected void onBookFlight() {
		try {
			mysqlAccess.addUserToFlight(flightSelected, user);
		} catch (Exception e) {
			// qqqq
		}
	}

	protected void onFlightCancel() {
		stage.setScene(getListFlightScene());
	}

	protected void onGotoHome() {
		stage.setScene(getHomeScene());
	}

	/**
	 * List of MyFlights
	 *//*
	public Scene getMyFlightsScene() {
		if (sceneMyFlights == null) {
			sceneMyFlights = new Scene(getMyFlightsPane(), width, height);
		}
		return sceneMyFlights;
	}

	public MyFlightsPane getMyFlightsPane() {
		if (paneMyFlights != null)
			return paneMyFlights;
		paneMyFlights = new MyFlightsPane(getMyFlightList()) {
			@Override
			protected void onFlightSelected(int row) {
				Flight flight = getSearchFlightList().get(row);
				MainApplication.this.onMyFlightSelected(flight);
			}

			@Override
			protected void onCancel() {
				MainApplication.this.onGotoHome();
			}
		};
		return paneMyFlights;
	}*/

	public ObservableList<Flight> getMyFlightList() {
		if (lstMyFlight == null) {
			lstMyFlight = FXCollections.observableArrayList();
			ArrayList<Flight> lst = mysqlAccess.getFlightsForUser(user);
			for (Flight f : lst) {
				getSearchFlightList().add(f);
			}
		}
		return lstMyFlight;
	}
/*
	protected void onMyFlightSelected(Flight flight) {
		this.myFlightSelected = flight;
		getMyFlightPane().setFlight(flight);
		stage.setScene(getMyFlightScene());
	}

	public Scene getMyFlightScene() {
		if (sceneMyFlight == null) {
			sceneBookFlight = new Scene(getBookFlightPane(), width, height);
		}
		return sceneBookFlight;
	}

	public MyFlightPane getMyFlightPane() {
		if (paneMyFlight != null)
			return paneMyFlight;

		paneMyFlight = new MyFlightPane() {
			@Override
			protected void onDeleteFlight() {
				MainApplication.this.onDeleteMyFlight();
			}

			@Override
			protected void onCancel() {
				stage.setScene(getMyFlightsScene());
			}

			@Override
			protected void onGotoHome() {
				stage.setScene(getHomeScene());
			}
		};
		return paneMyFlight;
	}*/

	protected void onDeleteMyFlight() {
		try {
			mysqlAccess.removeUserFromFlight(myFlightSelected, user);
			getMyFlightList().remove(myFlightSelected);
		} catch (Exception e) {
			// qqqqqqq
		}
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		stage.setTitle("Book-A-Flight");

		stage.setScene(getHomeScene());
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}

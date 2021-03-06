package gsu.edu.cis3270.project;

public class Flight {
	
	private int flightNumber;
	private String toCity;
	private String fromCity;
	private java.sql.Timestamp departureTime;
	private java.sql.Timestamp arrivalTime;
	private int passengers;

	public Flight(int flightNumber, String toCity, String fromCity, 
			java.sql.Timestamp departureTime, java.sql.Timestamp arrivalTime, int passengers){
		
		this.flightNumber = flightNumber;
		this.toCity = toCity;
		this.fromCity = fromCity;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.passengers = passengers;
	}
	
	public static int getCurrentPassengers(Flight flight){
		MySQLAccess counter = new MySQLAccess();
		int passengerCount = 0;
		
		try {
			 passengerCount = (counter.getPassengersForFlight(flight)).size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return passengerCount;
	}
	
	public Flight() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the flightNumber
	 */
	public int getFlightNumber() {
		return flightNumber;
	}
	/**
	 * @param flightNumber the flightNumber to set
	 */
	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}
	/**
	 * @return the toCity
	 */
	public String getToCity() {
		return toCity;
	}
	/**
	 * @param toCity the toCity to set
	 */
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}
	/**
	 * @return the fromCity
	 */
	public String getFromCity() {
		return fromCity;
	}
	/**
	 * @param fromCity the fromCity to set
	 */
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}
	/**
	 * @return the departureTime
	 */
	public java.sql.Timestamp getDepartureTime() {
		return departureTime;
	}
	/**
	 * @param departureTime the departureTime to set
	 */
	public void setDepartureTime(java.sql.Timestamp departureTime) {
		this.departureTime = departureTime;
	}
	/**
	 * @return the arrivalTime
	 */
	public java.sql.Timestamp getArrivalTime() {
		return arrivalTime;
	}
	/**
	 * @param arrivalTime the arrivalTime to set
	 */
	public void setArrivalTime(java.sql.Timestamp arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	/**
	 * @return the passengers
	 */
	public int getPassengers() {
		return passengers;
	}
	/**
	 * @param passengers the passengers to set
	 */
	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}
}

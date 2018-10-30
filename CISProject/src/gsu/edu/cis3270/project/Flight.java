package gsu.edu.cis3270.project;
import java.util.Date;

public class Flight {
	
	private int flightNumber;
	private String toCity;
	private String fromCity;
	private Date departureTime;
	private Date arrivalTime;
	private String[] passengers;

	public Flight(int flightNumber, String toCity, String fromCity, Date departureTime, Date arrivalTime, String[] passengers){
		this.flightNumber = flightNumber;
		this.toCity = toCity;
		this.fromCity = fromCity;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.passengers = passengers;
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
	public Date getDepartureTime() {
		return departureTime;
	}
	/**
	 * @param departureTime the departureTime to set
	 */
	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}
	/**
	 * @return the arrivalTime
	 */
	public Date getArrivalTime() {
		return arrivalTime;
	}
	/**
	 * @param arrivalTime the arrivalTime to set
	 */
	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	/**
	 * @return the passengers
	 */
	public String[] getPassengers() {
		return passengers;
	}
	/**
	 * @param passengers the passengers to set
	 */
	public void setPassengers(String[] passengers) {
		this.passengers = passengers;
	}
}

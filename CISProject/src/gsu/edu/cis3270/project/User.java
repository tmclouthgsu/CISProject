package gsu.edu.cis3270.project;

public class User {
	
	private String firstName;
	private String lastName;
	private String address;
	private String zip;
	private String state;
	private String username;
	private String password;
	private String email;
	private String SSN;
	private String secQ;
	private String secQAnswer;
	private Boolean isAdmin;

	public User(String firstName, String lastName, String address, String zip, String state, String username, String password, String email, String SSN, String secQ, String secQAnswer, Boolean isAdmin){
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.zip = zip;
		this.state = state;
		this.username = username;
		this.password = password;
		this.email = email;
		this.SSN = SSN;
		this.secQ = secQ;
		this.secQAnswer = secQAnswer;
		this.isAdmin = isAdmin;
		
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}
	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the sSN
	 */
	public String getSSN() {
		return SSN;
	}
	/**
	 * @param sSN the sSN to set
	 */
	public void setSSN(String sSN) {
		SSN = sSN;
	}
	/**
	 * @return the secQ
	 */
	public String getSecQ() {
		return secQ;
	}
	/**
	 * @param secQ the secQ to set
	 */
	public void setSecQ(String secQ) {
		this.secQ = secQ;
	}
	/**
	 * @return the secQAnswer
	 */
	public String getSecQAnswer() {
		return secQAnswer;
	}
	/**
	 * @param secQAnswer the secQAnswer to set
	 */
	public void setSecQAnswer(String secQAnswer) {
		this.secQAnswer = secQAnswer;
	}
	
	/**
	 * @return the isAdmin
	 */
	public Boolean getIsAdmin() {
		return isAdmin;
	}

	/**
	 * @param isAdmin the isAdmin to set
	 */
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}

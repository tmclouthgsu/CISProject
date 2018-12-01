package gsu.edu.cis3270.project;

public class User {
	
	protected String firstName;
	protected String lastName;
	protected String address;
	protected int zip;
	protected String state;
	protected String password;
	protected String email;
	protected int SSN;
	protected String secQ;
	protected String secQAnswer;
	protected int isAdmin;
	
	public User(){
		
	}

	public User(String firstName, String lastName, String address, 
				int zip, String state, String password, 
				String email, int SSN, String secQ, String secQAnswer, 
				int isAdmin){
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.zip = zip;
		this.state = state;
		this.password = password;
		this.email = email;
		this.SSN = SSN;
		this.secQ = secQ;
		this.secQAnswer = secQAnswer;
		this.isAdmin = isAdmin;
		
	}
	
	public boolean matchPassword(String password){
		if(this.password.matches(password)){
			return true;
		}
		else{
			return false;
		}
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
	public int getZip() {
		return zip;
	}
	/**
	 * @param zip the zip to set
	 */
	public void setZip(int zip) {
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
	public int getSSN() {
		return SSN;
	}
	/**
	 * @param sSN the sSN to set
	 */
	public void setSSN(int SSN) {
		this.SSN = SSN;
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
	public int getIsAdmin() {
		return isAdmin;
	}

	/**
	 * @param isAdmin the isAdmin to set
	 */
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}

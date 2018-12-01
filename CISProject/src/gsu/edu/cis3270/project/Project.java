package gsu.edu.cis3270.project;

public class Project {
	
	public static User registerUser(String firstName, String lastName, String address, 
				int zip, String state, String password, 
				String email, int SSN, String secQ, String secQAnswer){
		User registrant = new User(firstName,lastName,address,zip,state,password,
				email,SSN,secQ,secQAnswer,0);
		MySQLAccess userRegistration = new MySQLAccess();
		try {
				if(userRegistration.getUserFromDB(registrant.getEmail()) == null){
					userRegistration.insertUserToDB(registrant);
				}
				else{
					System.out.println("This username already belongs to a registered user");
				}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return registrant;
	}

	public static void main(String[] args) {
		
		registerUser("Bob","Tester","101 bobs house",12345,"Alaska",
				"mynameisbob","bobsemail@gmail.com",435679865,"my name is?","bob");
		
		

	}

}

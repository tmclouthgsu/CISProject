package gsu.edu.cis3270.project;

public class Test {
	
    public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		
		User bob = new User("Bob","Tester","101 bobs house",12345,"Alaska",
				"mynameisbob","bobsemail@gmail.com",435679865,"my name is?","bob",1);
		
		MySQLAccess dao = new MySQLAccess();
        
        System.out.println((dao.getUserFromDB("Bayek.Ofsiwa@nowhere.com")).getFirstName());
        
        //dao.insertUserToDB(bob);
        
        System.out.println("get flight from db " + (dao.getFlightFromDB(1753)).getFromCity());
        
        for(Flight temp : dao.searchByToCity("a")){
        	System.out.println("Search by to city " + temp.getFlightNumber());
        	System.out.println("Number of passengers on Flgiht: " + Flight.getCurrentPassengers(temp));
        }
        
        for(User temp : dao.getPassengersForFlight(dao.getFlightFromDB(2490))){
        	System.out.println("get passengers for flight " + temp.getPassword());
        }
        
        for(Flight temp : dao.getFlightsForUser(dao.getUserFromDB("Lev.Yashin@nowhere.com"))){
        	System.out.println("get flights for user " + temp.getFromCity());
        }
        
        for(Flight temp : dao.getAllFLights()){
        	System.out.println("get all flights for " + temp.getToCity());
        }
        
        
        
        dao.addUserToFlight(dao.getFlightFromDB(2683),dao.getUserFromDB("bob@tom.test"));
        
        //dao.removeUserFromFlight(dao.getFlightFromDB(3691),dao.getUserFromDB("Lev.Yashin@nowhere.com"));
        
        User.registerUser("Bob","Tester","101 bobs house",12345,"Alaska",
				"mynameisbob","bobsemail@gmail.com",435679865,"my name is?","bob");
        
        System.out.println();
    }

}

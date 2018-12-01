package gsu.edu.cis3270.project;

public class Admin extends User{
	
    public Admin() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		User bob = new User("Bob","Tester","101 bobs house",12345,"Alaska",
				"mynameisbob","bobsemail@gmail.com",435679865,"my name is?","bob",1);
		
        MySQLAccess dao = new MySQLAccess();
        //System.out.println((dao.getUserFromDB("Bayek.Ofsiwa@nowhere.com")).firstName);
        dao.insertUserToDB(bob);
    }

}

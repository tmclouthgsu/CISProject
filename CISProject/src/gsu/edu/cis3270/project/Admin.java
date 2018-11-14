package gsu.edu.cis3270.project;

public class Admin extends User{
	
    public static void main(String[] args) throws Exception {
        MySQLAccess dao = new MySQLAccess();
        dao.readDataBase();
    }

}

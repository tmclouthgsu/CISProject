package gsu.edu.cis3270.project;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLAccess {

   
    public User getUserFromDB(String username) throws Exception{
    	
        Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

    	String email = "";
    	String firstName  = "";
    	String lastName = "";
    	String address = "";
    	int zip = 0;
    	String state = "";
    	String password = "";
    	int ssn = 0;
    	String secQ = "";
    	String secQAnswer = "";
    	int isAdmin = 0;
    	User logInUser = new User();

    	try{
    		connect = DriverManager
    				.getConnection("jdbc:mysql://localhost/ProjectDB?"
    						+ "user=root&password=password");
    		preparedStatement = connect.prepareStatement("select * from ProjectDB.User where EMAIL = ? ; ");
    		preparedStatement.setString(1, username);
    		resultSet = preparedStatement.executeQuery();
    		
        	while (resultSet.next()){
        		email = resultSet.getString(1);
        		firstName = resultSet.getString(2);
        		lastName = resultSet.getString(3);
        		address = resultSet.getString(4);
        		zip = resultSet.getInt(5);
        		state = resultSet.getString(6);
        		password = resultSet.getString(7);
        		ssn = resultSet.getInt(8);
        		secQ = resultSet.getString(9);
        		secQAnswer = resultSet.getString(10);
        		isAdmin = resultSet.getInt(11);
        	}

    	} catch (Exception e) {
    		throw e;
    	} finally {
    		close(resultSet,connect,statement);
    	}

    	if(email != null){

    		logInUser = new User(firstName, lastName, address, 
    				zip, state, password, email, ssn, secQ, secQAnswer,isAdmin);
    	}
    	return logInUser; 

    }

    public void insertUserToDB(User user) throws Exception{
    	
        Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


    	try{

    		connect = DriverManager
    				.getConnection("jdbc:mysql://localhost/ProjectDB?"
    						+ "user=root&password=password");
    		preparedStatement = connect.prepareStatement("insert into  ProjectDB.User values (?,?,?,?,?,?,?,?,?,?,?)");
    		preparedStatement.setString(1, user.getEmail());
    		preparedStatement.setString(2, user.getFirstName());
    		preparedStatement.setString(3, user.getLastName());
    		preparedStatement.setString(4, user.getAddress());
    		preparedStatement.setInt(5, user.getZip());
    		preparedStatement.setString(6, user.getState());
    		preparedStatement.setString(7, user.getPassword());
    		preparedStatement.setInt(8, user.getSSN());
    		preparedStatement.setString(9, user.getSecQ());
    		preparedStatement.setString(10, user.getSecQAnswer());
    		preparedStatement.setInt(11, user.getIsAdmin());
    		preparedStatement.executeUpdate();

    	}catch(Exception e) {
    		throw e;
    	}finally {
    		close(resultSet,connect,statement);
    	}

    }
 
    public Flight getFlightFromDB(int flightnumber) throws Exception{
    	
        Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    	
    	String toCity = "";
    	String fromCity  = "";
    	java.sql.Date departureTime = null;
    	java.sql.Date arrivalTime = null;
    	int passengers = 0;
    	int flightNumber = 0;
    	Flight currentFlight = new Flight();

    	try{
    		connect = DriverManager
    				.getConnection("jdbc:mysql://localhost/ProjectDB?"
    						+ "user=root&password=password");
    		preparedStatement = connect.prepareStatement("select * from ProjectDB.Flight where FLIGHTNUMBER = ? ; ");
    		preparedStatement.setInt(1, flightnumber);
    		resultSet = preparedStatement.executeQuery();
    		
        	while (resultSet.next()){
        		flightNumber = resultSet.getInt(1);
        		toCity = resultSet.getString(2);
        		fromCity = resultSet.getString(3);
        		departureTime = resultSet.getDate(4);
        		arrivalTime = resultSet.getDate(5);
        		passengers = resultSet.getInt(6);
        	}

    	} catch (Exception e) {
    		throw e;
    	} finally {
    		close(resultSet,connect,statement);
    	}

    	if(flightNumber != 0){

    		currentFlight = new Flight(flightNumber, toCity, fromCity, 
    				departureTime, arrivalTime, passengers);
    	}
    	return currentFlight; 
    	
    }

    public void insertFlightToDB(Flight flight) throws Exception{
    	
        Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    	
    	try{

    		connect = DriverManager
    				.getConnection("jdbc:mysql://localhost/ProjectDB?"
    						+ "user=root&password=password");
    		preparedStatement = connect.prepareStatement("insert into  ProjectDB.User values (?,?,?,?,?,?)");
    		preparedStatement.setInt(1, flight.getFlightNumber());
    		preparedStatement.setString(2, flight.getToCity());
    		preparedStatement.setString(3, flight.getFromCity());
    		preparedStatement.setDate(4, flight.getDepartureTime());
    		preparedStatement.setDate(5, flight.getArrivalTime());
    		preparedStatement.setInt(6, flight.getPassengers());
    		preparedStatement.executeUpdate();

    	}catch(Exception e) {
    		throw e;
    	}finally {
    		close(resultSet,connect,statement);
    	}   	
    }
    
    private ArrayList<Flight> listFlights(ResultSet rs){
    	
    	
    	ArrayList<Flight> flightList = new ArrayList<Flight>(); 
    	
    	try {
    		
    		if(rs.getMetaData().getColumnCount() == 6){
    			rs.beforeFirst();
    			while (rs.next()){
    				flightList.add(new Flight(rs.getInt(1),rs.getString(2),
    						rs.getString(3),rs.getDate(4),
    						rs.getDate(5),rs.getInt(6)));		
    			}
    		}
    		else{
    			rs.beforeFirst();
    			while (rs.next()){
    				flightList.add(getFlightFromDB(rs.getInt(1)));		
    			}
    		}
    		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return flightList;
    	
    }

    public ArrayList<Flight> searchByToCity(String searchCriteria) throws Exception{
    	
        Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    	
    	ArrayList<Flight> results = null;
    	
    	try{
    		connect = DriverManager
    				.getConnection("jdbc:mysql://localhost/ProjectDB?"
    						+ "user=root&password=password");
    		preparedStatement = connect.prepareStatement("select * from ProjectDB.Flight where TOCITY like ? ; ");
    		preparedStatement.setString(1, "%" + searchCriteria + "%");
    		resultSet = preparedStatement.executeQuery();
    		results = listFlights(resultSet);
    		
    	} catch (Exception e) {
    		throw e;
    	} finally {
    		close(resultSet,connect,statement);
    	}
    	
    	return results;
    	
    	
    	
    }
       
    public ArrayList<Flight> searchByFromCity(String searchCriteria) throws Exception{
    	
        Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    	
    	ArrayList<Flight> results = null;
    	
    	try{
    		connect = DriverManager
    				.getConnection("jdbc:mysql://localhost/ProjectDB?"
    						+ "user=root&password=password");
    		preparedStatement = connect.prepareStatement("select * from ProjectDB.Flight where FROMCITY like ? ; ");
    		preparedStatement.setString(1, "%" + searchCriteria + "%");
    		resultSet = preparedStatement.executeQuery();
    		results = listFlights(resultSet);
    		
    	} catch (Exception e) {
    		throw e;
    	} finally {
    		close(resultSet,connect,statement);
    	}
    	
    	return results;
    	
    	
    	
    }
      
	public ArrayList<User> getPassengersForFlight(Flight flight) throws Exception{
    	
        Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    	
    	ArrayList<User> userList = new ArrayList<User>();
    	
    	try {
    		
    		connect = DriverManager
    				.getConnection("jdbc:mysql://localhost/ProjectDB?"
    						+ "user=root&password=password");
    		preparedStatement = connect.prepareStatement("select * from ProjectDB.UserFlight where FLIGHTNUMBER = ? ; ");
    		preparedStatement.setInt(1, flight.getFlightNumber());
    		resultSet = preparedStatement.executeQuery();
   		
			while (resultSet.next()){
				userList.add(getUserFromDB(resultSet.getString("USEREMAIL")));		
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(resultSet,connect,statement);
		}
    	return userList;
    }
    
	public ArrayList<Flight> getFlightsForUser(User user){
        
		Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    	
    	ArrayList<Flight> flightList = new ArrayList<Flight>();
    	
    	try {
    		
    		connect = DriverManager
    				.getConnection("jdbc:mysql://localhost/ProjectDB?"
    						+ "user=root&password=password");
    		preparedStatement = connect.prepareStatement("select * from ProjectDB.UserFlight where USEREMAIL = ? ; ");
    		preparedStatement.setString(1, user.getEmail());
    		resultSet = preparedStatement.executeQuery();
   		
			if(resultSet.next()){
				flightList = listFlights(resultSet);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(resultSet,connect,statement);
		}
    	return flightList;
    }
	
	public ArrayList<Flight> getAllFLights(){
        
		Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    	
    	ArrayList<Flight> flightList = new ArrayList<Flight>();
    	
    	try {
    		
    		connect = DriverManager
    				.getConnection("jdbc:mysql://localhost/ProjectDB?"
    						+ "user=root&password=password");
    		preparedStatement = connect.prepareStatement("select * from ProjectDB.Flight");
    		resultSet = preparedStatement.executeQuery();
   		
			if(resultSet.next()){
				flightList = listFlights(resultSet);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(resultSet,connect,statement);
		}
    	return flightList;
    }

    public int addUserToFlight(Flight flight, User user) throws Exception{
    	
        Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    	
    	try{

    		connect = DriverManager
    				.getConnection("jdbc:mysql://localhost/ProjectDB?"
    						+ "user=root&password=password");

    		for(User passenger : getPassengersForFlight(flight)){
    			if(user.getEmail().matches(passenger.getEmail())){
    				close(resultSet,connect,statement);
    				System.out.println("You are already registered for this Flight");
    				return 0;
    			}
    		}
    		
    		preparedStatement = connect.prepareStatement("insert into  ProjectDB.UserFlight values (?,?)");
    		preparedStatement.setInt(1, flight.getFlightNumber());
    		preparedStatement.setString(2, user.getEmail());
    		preparedStatement.executeUpdate();

    	}catch(Exception e) {
    		throw e;
    	}finally {
    		close(resultSet,connect,statement);
    	}
    	return 1;
    	
    }
  
    public void removeUserFromFlight(Flight flight, User user) throws Exception{
    	
        Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    	
    	try{

    		connect = DriverManager
    				.getConnection("jdbc:mysql://localhost/ProjectDB?"
    						+ "user=root&password=password");

    		for(User passenger : getPassengersForFlight(flight)){
    			
    			if(user.getEmail().matches(passenger.getEmail())){
    				
    				preparedStatement = connect.prepareStatement("delete from ProjectDB.UserFlight "
    						+ "where FLIGHTNUMBER = ? AND USEREMAIL = ? ");
    	    		preparedStatement.setInt(1, flight.getFlightNumber());
    	    		preparedStatement.setString(2, user.getEmail());
    	    		preparedStatement.executeUpdate();  				
    			}
    		}
    	}catch(Exception e) {
    		throw e;
    	}finally {
    		close(resultSet,connect,statement);
    	}  
    	
    }
    
    private void close(ResultSet rs, Connection connect, Statement statement) {
        try {
            if (rs != null) {
                rs.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

}

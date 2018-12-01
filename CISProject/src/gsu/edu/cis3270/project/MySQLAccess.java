package gsu.edu.cis3270.project;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class MySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

   
    public User getUserFromDB(String username) throws Exception{

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
    		close();
    	}

    	if(email != null){

    		logInUser = new User(firstName, lastName, address, 
    				zip, state, password, email, ssn, secQ, secQAnswer,isAdmin);
    	}
    	return logInUser; 

    }

    public void insertUserToDB(User user) throws Exception{


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
    	}

    }
 
    public Flight getFlightFromDB(int flightnumber) throws Exception{
    	
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
    		close();
    	}

    	if(flightNumber != 0){

    		currentFlight = new Flight(flightNumber, toCity, fromCity, 
    				departureTime, arrivalTime, passengers);
    	}
    	return currentFlight; 
    	
    }

    public void insertFlightToDB(Flight flight) throws Exception{
    	
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
    	}   	
    }
    
    public ArrayList<Flight> listFlights(ResultSet rs){
    	
    	ArrayList<Flight> flightList = new ArrayList<Flight>();
    	
    	try {
			while (rs.next()){
				flightList.add(new Flight(resultSet.getInt(1),resultSet.getString(2),
						resultSet.getString(3),resultSet.getDate(4),
						resultSet.getDate(5),resultSet.getInt(6)));		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return flightList;
    	
    }

    public ArrayList<Flight> searchByToCity(String searchCriteria) throws Exception{
    	
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
    		close();
    	}
    	
    	return results;
    	
    	
    	
    }
    
    
    private void writeMetaData(ResultSet resultSet) throws SQLException {
        //  Now get some metadata from the database
        // Result set get the result of the SQL query

        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
            System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
        }
    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            String user = resultSet.getString("myuser");
            String website = resultSet.getString("webpage");
            String summary = resultSet.getString("summary");
            Date date = resultSet.getDate("datum");
            String comment = resultSet.getString("comments");
            System.out.println("User: " + user);
            System.out.println("Website: " + website);
            System.out.println("summary: " + summary);
            System.out.println("Date: " + date);
            System.out.println("Comment: " + comment);
        }
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
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

package gsu.edu.cis3270.project;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void readDataBase() throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
            //Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/ProjectDB?"
                            + "user=root&password=password");

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement
                    .executeQuery("select * from ProjectDB.comments");
            writeResultSet(resultSet);

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect
                    .prepareStatement("insert into  ProjectDB.comments values (default, ?, ?, ?, ? , ?, ?)");
            // "myuser, webpage, datum, summary, COMMENTS from ProjectDB.comments");
            // Parameters start with 1
            preparedStatement.setString(1, "Test");
            preparedStatement.setString(2, "TestEmail");
            preparedStatement.setString(3, "TestWebpage");
            preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
            preparedStatement.setString(5, "TestSummary");
            preparedStatement.setString(6, "TestComment");
            preparedStatement.executeUpdate();

            preparedStatement = connect
                    .prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from ProjectDB.comments");
            resultSet = preparedStatement.executeQuery();
            writeResultSet(resultSet);

            // Remove again the insert comment
            preparedStatement = connect
            .prepareStatement("delete from ProjectDB.comments where myuser= ? ; ");
            preparedStatement.setString(1, "Test");
            preparedStatement.executeUpdate();

            resultSet = statement
            .executeQuery("select * from ProjectDB.comments");
            writeMetaData(resultSet);

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }
    
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

    	if(ssn != 0){

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
    		preparedStatement.setString(1, user.email);
    		preparedStatement.setString(2, user.firstName);
    		preparedStatement.setString(3, user.lastName);
    		preparedStatement.setString(4, user.address);
    		preparedStatement.setInt(5, user.zip);
    		preparedStatement.setString(6, user.state);
    		preparedStatement.setString(7, user.password);
    		preparedStatement.setInt(8, user.SSN);
    		preparedStatement.setString(9, user.secQ);
    		preparedStatement.setString(10, user.secQAnswer);
    		preparedStatement.setInt(11, user.isAdmin);
    		preparedStatement.executeUpdate();

    	}catch(Exception e) {
    		throw e;
    	}finally {
    	}

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

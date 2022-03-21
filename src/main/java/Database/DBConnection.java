package Database;

import Password.Password;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

    private static final String protocol = "jdbc";
    private static final String vendorName = "";
    private static final String ipAddress = "";
    private static final String dbName = "";

    private static final String jdbcURL = protocol + vendorName + ipAddress + dbName;

    private static final String MYSQLJDBCDriver = "";

    private static final String username = "";
    public static Connection conn = null;

    public static Connection startConnection(){
        try{
           Class.forName(MYSQLJDBCDriver);
           conn = DriverManager.getConnection(jdbcURL, username, Password.getPassword());
           System.out.println("Connection Successful");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(){
        try{
            conn.close();
        }
        catch (Exception e){
            //do nothing
        }
    }

}
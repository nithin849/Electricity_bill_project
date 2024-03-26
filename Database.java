//import com.sql.jdbc.Connection;
import java.sql.*;
public class Database {
    Connection connection;
    Statement statement;
    Database(){
        try{
            connection=DriverManager.getConnection("jdbc:mysql://localhost/bill", "root", "root");
            statement=connection.createStatement();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
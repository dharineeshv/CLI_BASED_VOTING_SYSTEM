package Voters;
import java.sql.*;

public class DBO {
    public static Connection get_connection() throws Exception
    {
        String url = "jdbc:mysql://localhost:3306/VOTING_SYSTEMS";
        String pwd = "DHARINEE18";
        String name = "root";
        Connection conn = DriverManager.getConnection(url,name,pwd);
        return conn;
    }
}
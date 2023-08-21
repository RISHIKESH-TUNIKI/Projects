import java.sql.*;

public class conn {

    Connection c;
    Statement s;
    conn(){
        try {
            c=DriverManager.getConnection("jdbc:mysql:///atmsimulator","root","Rishikh@1532");
            s=c.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

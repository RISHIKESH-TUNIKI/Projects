import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        conn c=new conn();
        String query="select *from clientpersonaldetails";
        try {
            ResultSet rs=c.s.executeQuery(query);
            while (rs.next()){
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}


import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnect {

    public static void main(String args[]) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/test?"
                    + "user=root");
            System.out.println("Noi ket thanh cong");
        } catch (Exception ex) { //xử lý ngoại lệ nếu có
            System.out.println("Noi ket khong thanh cong");
            ex.printStackTrace();
        }
    }
}

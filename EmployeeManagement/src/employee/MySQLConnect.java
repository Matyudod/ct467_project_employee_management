package employee;


import employee.PhongBanClass;
import employee.PhongBanService;
import employee.NhanVienClass;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySQLConnect {

    public static void main(String args[]) {
        
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/quanlythongtinnv?"
                    + "user=root");
            System.out.println("Noi ket thanh cong");
            
            PhongBanService pbs = new PhongBanService();
            List<PhongBanClass> danhSachPhongBan = new ArrayList<PhongBanClass>();
            danhSachPhongBan = pbs.layDanhSachPhongBan(conn);
            pbs.inDanhSachPhongBan(danhSachPhongBan);
            
            
        } catch (Exception ex) { //xử lý ngoại lệ nếu có
            System.out.println("Noi ket khong thanh cong");
            ex.printStackTrace();
        }
    }
}


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class MySQLConnect {

    public static void main(String args[]) {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/quanlythongtinnv?"
                    + "user=root");
            System.out.println("Noi ket thanh cong");
            
            pStmt = conn.prepareStatement("SELECT * FROM nhanvien");
            rs = pStmt.executeQuery();
            while (rs.next()) {
                String maNhanVien = rs.getString("maNhanVien"); 
                String maChucVu = rs.getString("maChucVu");
                String maPhongBan = rs.getString("maPhongBan");
                String hoTen = rs.getString("hoTen");
                String diaChi = rs.getString("diaChi");
                String sdt = rs.getString("sdt");
                System.out.println(maNhanVien + "\t " + hoTen);
                System.out.println("-------------");
                
           }
            
        } catch (Exception ex) { //xử lý ngoại lệ nếu có
            System.out.println("Noi ket khong thanh cong");
            ex.printStackTrace();
        }
    }
}

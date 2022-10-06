package employee;


import employee.PhongBanClass;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PhongBanService {

    public List<PhongBanClass> layDanhSachPhongBan(Connection conn) {
        List<PhongBanClass> danhSachPhongBan = new ArrayList<PhongBanClass>();
        try {
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM phongban");
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                int maPhongBan = rs.getInt("maPhongBan");
                String tenPhongBan = rs.getString("tenPhongBan");
                PhongBanClass a = new PhongBanClass(maPhongBan, tenPhongBan);
                danhSachPhongBan.add(a);
            }
            
        } catch (Exception ex) { //xử lý ngoại lệ nếu có
            System.out.println("Nối kết không thành công");
            ex.printStackTrace();
        }
        return danhSachPhongBan;
    }
    
    public void themPhongBan(Connection conn,String tenPhongBan) {
        
        try {
            CallableStatement cStmt = conn.prepareCall("{call ThemPhongBan(?)}");
            cStmt.setString(1, tenPhongBan);
            cStmt.executeQuery();
            
            System.out.println("Thêm phòng ban thành công!");
            System.out.println("----------------------------");
            
        } catch (Exception ex) { //xử lý ngoại lệ nếu có
            System.out.println("Thêm phòng ban không thành công");
            ex.printStackTrace();
        }
    }
    
    public void capNhatPhongBan(Connection conn,int maPhongBan, String tenPhongBan) {
        
        try {
            CallableStatement cStmt = conn.prepareCall("{call SuaPhongBan(?,?)}");
            cStmt.setInt(1, maPhongBan);
            cStmt.setString(2, tenPhongBan);
            cStmt.executeQuery();
            
            System.out.println("Cập nhật phòng ban thành công!");
            System.out.println("----------------------------");
            
        } catch (Exception ex) { //xử lý ngoại lệ nếu có
            System.out.println("Cập nhật phòng ban không thành công");
            ex.printStackTrace();
        }
    }
    
    public void xoaPhongBan(Connection conn,int maPhongBan) {
        
        try {
            CallableStatement cStmt = conn.prepareCall("{call XoaPhongBan(?)}");
            cStmt.setInt(1, maPhongBan);
            cStmt.executeQuery();
            
            System.out.println("Xoá phòng ban thành công!");
            System.out.println("----------------------------");
            
        } catch (Exception ex) { //xử lý ngoại lệ nếu có
            System.out.println("Xoá phòng ban không thành công");
            ex.printStackTrace();
        }
    }

    public void inDanhSachPhongBan(List<PhongBanClass> danhSachPhongBan) {

        danhSachPhongBan.forEach((phongban) -> {
            System.out.println(phongban.layMaPhongBan() + "\t " + phongban.layTenPhongBan());
        });

        System.out.println("----------------------------");
    }
}

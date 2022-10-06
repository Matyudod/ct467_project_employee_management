
package employee;

import employee.ChucVuClass;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChucVuService {
    public List<ChucVuClass> layDanhSachChucVu(Connection conn) {
        List<ChucVuClass> danhSachChucVu = new ArrayList<ChucVuClass>();
        try {
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM chucvu");
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                int maChucVu = rs.getInt("maChucVu");
                String tenChucVu = rs.getString("tenChucVu");
                ChucVuClass a = new ChucVuClass(maChucVu, tenChucVu);
                danhSachChucVu.add(a);
            }
            
        } catch (Exception ex) { //xử lý ngoại lệ nếu có
            System.out.println("Nối kết không thành công");
            ex.printStackTrace();
        }
        return danhSachChucVu;
    }
    
    public void themChucVu(Connection conn,String tenChucVu) {
        
        try {
            CallableStatement cStmt = conn.prepareCall("{call ThemChucVu(?)}");
            cStmt.setString(1, tenChucVu);
            cStmt.executeQuery();
            
            System.out.println("Thêm chức vụ thành công!");
            System.out.println("----------------------------");
            
        } catch (Exception ex) { //xử lý ngoại lệ nếu có
            System.out.println("Thêm chức vụ không thành công");
            ex.printStackTrace();
        }
    }
    
    public void capNhatChucVu(Connection conn,int maChucVu, String tenChucVu) {
        
        try {
            CallableStatement cStmt = conn.prepareCall("{call SuaChucVu(?,?)}");
            cStmt.setInt(1, maChucVu);
            cStmt.setString(2, tenChucVu);
            cStmt.executeQuery();
            
            System.out.println("Cập nhật chức vụ thành công!");
            System.out.println("----------------------------");
            
        } catch (Exception ex) { //xử lý ngoại lệ nếu có
            System.out.println("Cập nhật chức vụ không thành công");
            ex.printStackTrace();
        }
    }
    
    public void xoaChucVu(Connection conn,int maChucVu) {
        
        try {
            CallableStatement cStmt = conn.prepareCall("{call XoaChucVu(?)}");
            cStmt.setInt(1, maChucVu);
            cStmt.executeQuery();
            
            System.out.println("Xoá chức vụ thành công!");
            System.out.println("----------------------------");
            
        } catch (Exception ex) { //xử lý ngoại lệ nếu có
            System.out.println("Xoá chức vụ không thành công");
            ex.printStackTrace();
        }
    }

    public void inDanhSachChucVu(List<ChucVuClass> danhSachChucVu) {

        danhSachChucVu.forEach((chucvu) -> {
            System.out.println(chucvu.layMaChucVu() + "\t " + chucvu.layTenChucVu());
        });

        System.out.println("----------------------------");
    }
}

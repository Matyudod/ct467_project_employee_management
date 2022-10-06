package employee;


import employee.PhongBanClass;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
            System.out.println("Noi ket khong thanh cong");
            ex.printStackTrace();
        }
        return danhSachPhongBan;
    }

    public void inDanhSachPhongBan(List<PhongBanClass> danhSachPhongBan) {

        System.out.println("----------------------------");
        danhSachPhongBan.forEach((phongban) -> {
            System.out.println(phongban.layMaPhongBan() + "\t " + phongban.layTenPhongBan());

            System.out.println("----------------------------");
        });

    }
}

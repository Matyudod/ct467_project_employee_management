
package employee;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienService {
    public List<NhanVienClass> layDanhSachNhanVien(Connection conn) {
        List<NhanVienClass> danhSachNhanVien = new ArrayList<NhanVienClass>();
        try {
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM nhanvien");
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                int maNhanVien = rs.getInt("maNhanVien");
                
                int maChucVu = rs.getInt("maChucVu");
                PreparedStatement cvStmt = conn.prepareStatement("SELECT * FROM chucvu where maChucVu=?");               
                cvStmt.setInt(1, maChucVu);
                ResultSet rscv = cvStmt.executeQuery();
                ChucVuClass chucVu =new ChucVuClass();
                while (rscv.next()) {
                    int ma = rscv.getInt("maChucVu");
                    String tenChucVu = rscv.getString("tenChucVu");
                    chucVu = new ChucVuClass(ma, tenChucVu);
                }
                
                int maPhongBan = rs.getInt("maPhongBan");
                PreparedStatement pbStmt = conn.prepareStatement("SELECT * FROM phongban where maPhongBan=?");               
                pbStmt.setInt(1, maPhongBan);
                ResultSet rspb = pbStmt.executeQuery();
                PhongBanClass phongBan=new PhongBanClass();
                while (rspb.next()) {
                    int ma = rspb.getInt("maPhongBan");
                    String tenPhongBan = rspb.getString("tenPhongBan");
                    phongBan = new PhongBanClass(ma, tenPhongBan);
                }
                
                String hoTen = rs.getString("hoTen");
                String diaChi = rs.getString("diaChi");
                String sdt = rs.getString("sdt");
                NhanVienClass a = new NhanVienClass(maNhanVien, chucVu, phongBan, hoTen, diaChi, sdt);
                danhSachNhanVien.add(a);
            }
            
        } catch (Exception ex) { //xử lý ngoại lệ nếu có
            System.out.println("Nối kết không thành công");
            ex.printStackTrace();
        }
        return danhSachNhanVien;
    }
    public void themNhanVien(Connection conn, int maChucVu , int maPhongBan ,String hoTen, String diaChi,String sdt) {
        
        try {
            CallableStatement cStmt = conn.prepareCall("INSERT INTO nhanvien (maChucVu, maPhongBan, hoTen, diaChi, sdt) VALUES (?,?,?,?,?)");
            cStmt.setInt(1, maChucVu);
            cStmt.setInt(2, maPhongBan);
            cStmt.setString(3, hoTen);
            cStmt.setString(4, diaChi);
            cStmt.setString(5, sdt);
            cStmt.executeUpdate();
            
            System.out.println("Thêm nhân viên thành công!");
            System.out.println("----------------------------");
            
        } catch (Exception ex) { //xử lý ngoại lệ nếu có
            System.out.println("Thêm nhân viên không thành công");
            ex.printStackTrace();
        }
    }
    public void capNhatNhanVien(Connection conn,int maNhanVien, int maChucVu , int maPhongBan ,String hoTen, String diaChi,String sdt) {
        
        try {
            CallableStatement cStmt = conn.prepareCall("{call suaNhanVien(?, ?, ?, ?, ?, ?)}");
            cStmt.setInt(1, maNhanVien);
            cStmt.setInt(2, maChucVu);
            cStmt.setInt(3, maPhongBan);
            cStmt.setString(4, hoTen);
            cStmt.setString(5, diaChi);
            cStmt.setString(6, sdt);
            cStmt.executeQuery();
            
            System.out.println("Cập nhật nhân viên thành công!");
            System.out.println("----------------------------");
            
        } catch (Exception ex) { //xử lý ngoại lệ nếu có
            System.out.println("Cập nhật nhân viên không thành công");
            ex.printStackTrace();
        }
    }
    public void xoaNhanVien(Connection conn,int maNhanVien) {
        
        try {
            CallableStatement cStmt = conn.prepareCall("{call xoaNhanVien(?)}");
            cStmt.setInt(1, maNhanVien);
            cStmt.executeQuery();
            
            System.out.println("Xoá nhân vien thành công!");
            System.out.println("----------------------------");
            
        } catch (Exception ex) { //xử lý ngoại lệ nếu có
            System.out.println("Xoá nhân viên không thành công");
            ex.printStackTrace();
        }
    }
    public void inDanhSachNhanVien(List<NhanVienClass> danhSachNhanVien) {

        danhSachNhanVien.forEach((nhanvien) -> {
            System.out.println(nhanvien.layMaNhanVien() + "\t " +nhanvien.layChucVuNhanVien() + "\t " +nhanvien.layPhongBanNhanVien() + "\t " +nhanvien.layHoTenNhanVien() + "\t " +nhanvien.layDiaChiNhanVien() + "\t " +nhanvien.laysdtNhanVien());
        });

        System.out.println("----------------------------");
    }
    public void timNhanVienTheoMaNV(Connection conn,int maNhanVien) {
        try {
            PreparedStatement cvStmt = conn.prepareStatement("{call timNhanVienTheoMaNV(?)}");               
            cvStmt.setInt(1, maNhanVien);
            ResultSet rsnv = cvStmt.executeQuery();
            int count = 0;   
            while (rsnv.next()) {
                count++;
                int maNV = rsnv.getInt("maNhanVien");
                int maCV = rsnv.getInt("maChucVu");
                int maPB = rsnv.getInt("maPhongBan");
                String hoTen = rsnv.getString("hoTen");
                String diaChi = rsnv.getString("diaChi");
                String sdt = rsnv.getString("sdt");
                System.out.println(maNV + "\t " +maCV + "\t " +maPB + "\t " +hoTen + "\t " +diaChi + "\t " +sdt);
            }

            if (count == 0) {System.out.println("Không tồn tại nhân viên có mã này!");}
        } catch (Exception ex) { //xử lý ngoại lệ nếu có
            System.out.println("Không tồn tại nhân viên này!");
            ex.printStackTrace();
        }
    }
    public void timNhanVienTheoTenNV(Connection conn,String tenNhanVien) {
        try {
            PreparedStatement cvStmt = conn.prepareStatement("{call timNhanVienTheoTenNV(?)}");               
            cvStmt.setString(1, tenNhanVien);
            ResultSet rsnv = cvStmt.executeQuery();
            int count = 0;         
            while (rsnv.next()) {
                count++;
                int maNV = rsnv.getInt("maNhanVien");
                int maCV = rsnv.getInt("maChucVu");
                int maPB = rsnv.getInt("maPhongBan");
                String hoTen = rsnv.getString("hoTen");
                String diaChi = rsnv.getString("diaChi");
                String sdt = rsnv.getString("sdt");
                System.out.println(maNV + "\t " +maCV + "\t " +maPB + "\t " +hoTen + "\t " +diaChi + "\t " +sdt);
            }
           
            if (count == 0) {System.out.println("Không tồn tại nhân viên có tên này!");}
        } catch (Exception ex) { //xử lý ngoại lệ nếu có
            System.out.println("Không tồn tại nhân viên này!");
            ex.printStackTrace();
        }
    }
        public void timNhanVienTheoMaChucVu(Connection conn,int maChucVu) {
        try {
            PreparedStatement cvStmt = conn.prepareStatement("{call timNhanVienTheoMaChucVu(?)}");               
            cvStmt.setInt(1, maChucVu);
            ResultSet rsnv = cvStmt.executeQuery();
            int count = 0;         
            while (rsnv.next()) {
                count++;
                int maNV = rsnv.getInt("maNhanVien");
                int maCV = rsnv.getInt("maChucVu");
                int maPB = rsnv.getInt("maPhongBan");
                String hoTen = rsnv.getString("hoTen");
                String diaChi = rsnv.getString("diaChi");
                String sdt = rsnv.getString("sdt");
                System.out.println(maNV + "\t " +maCV + "\t " +maPB + "\t " +hoTen + "\t " +diaChi + "\t " +sdt);
            }
           
            if (count == 0) {System.out.println("Không tồn tại nhân viên có mã chức vụ này!");}
        } catch (Exception ex) { //xử lý ngoại lệ nếu có
            System.out.println("Không tồn tại nhân viên này!");
            ex.printStackTrace();
        }
    }
}

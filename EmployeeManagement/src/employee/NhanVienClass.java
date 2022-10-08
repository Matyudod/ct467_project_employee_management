package employee;

import java.util.List;

public class NhanVienClass {
    private int maNhanVien ;
    private ChucVuClass chucVu ;
    private PhongBanClass phongBan;
    private String hoTen;
    private String diaChi;
    private String sdt;

    public NhanVienClass() {
        this.maNhanVien = 0;
        this.chucVu = new ChucVuClass();
        this.phongBan = new PhongBanClass();
        this.hoTen = new String("Undefined");
        this.diaChi = new String("Undefined");
        this.sdt = new String("Undefined");
    }

    public NhanVienClass(int maNhanVien, ChucVuClass chucVu , PhongBanClass phongBan, String hoTen, String diaChi, String sdt) {
        this.maNhanVien = maNhanVien;
        this.chucVu = new ChucVuClass(chucVu.layMaChucVu(), chucVu.layTenChucVu());
        this.phongBan = new PhongBanClass (phongBan.layMaPhongBan(), phongBan.layTenPhongBan());
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }
    public int layMaNhanVien() {
        return this.maNhanVien;
    }

    public String layHoTenNhanVien() {
        return this.hoTen;
    }
    public String layDiaChiNhanVien() {
        return this.diaChi;
    }
    public String laysdtNhanVien() {
        return this.sdt;
    }
    public String layChucVuNhanVien() {
        return this.chucVu.layTenChucVu();
    }
    public String layPhongBanNhanVien() {
        return this.phongBan.layTenPhongBan();
    }

}
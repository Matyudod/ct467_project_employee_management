package employee;


import java.util.List;

public class PhongBanClass {

    private int maPhongBan;
    private String tenPhongBan;

    public PhongBanClass() {
        this.maPhongBan = 0;
        this.tenPhongBan = new String("Undefined");
    }

    public PhongBanClass(int maPhongBan, String tenPhongBan) {
        this.maPhongBan = maPhongBan;
        this.tenPhongBan = tenPhongBan;
    }
    public PhongBanClass(int maPhongBan) {
        this.maPhongBan = maPhongBan;
        this.tenPhongBan = new String("Undefined");
    }

    public int layMaPhongBan() {
        return this.maPhongBan;
    }

    public String layTenPhongBan() {
        return this.tenPhongBan;
    }

    
}

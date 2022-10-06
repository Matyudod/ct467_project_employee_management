
package employee;

public class ChucVuClass {
    private int maChucVu;
    private String tenChucVu;

    public ChucVuClass() {
        this.maChucVu = 0;
        this.tenChucVu = new String("Undefined");
    }

    public ChucVuClass(int maChucVu, String tenChucVu) {
        this.maChucVu = maChucVu;
        this.tenChucVu = tenChucVu;
    }
    public ChucVuClass(int maChucVu) {
        this.maChucVu = maChucVu;
        this.tenChucVu = new String("Undefined");
    }

    public int layMaChucVu() {
        return this.maChucVu;
    }

    public String layTenChucVu() {
        return this.tenChucVu;
    }
}

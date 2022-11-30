package employee;

import employee.PhongBanClass;
import employee.PhongBanService;
import employee.ChucVuClass;
import employee.ChucVuService;
import employee.NhanVienClass;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MySQLConnect {

    public static void main(String args[]) {

        Connection conn = null;
        try {
            Scanner sc = new Scanner(System.in);
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/quanlythongtinnv?"
                    + "user=root");
            System.out.println("Noi ket thanh cong"); 
            int choise = 0;
            int subChoise = 0;
            while (true) {
                if (choise == 0) {
                    System.out.println("---------------------------------");
                    System.out.println("1: Quản lí nhân viên");
                    System.out.println("2: Quản lí chức vụ");
                    System.out.println("3: Quản lí phòng ban");
                    System.out.println("-1: Thoát");
                    System.out.print("Vui lòng nhập lựa chọn của bạn:");
                    choise = sc.nextInt();
                    System.out.println("---------------------------------");
                } else if (choise == 1) {
                    System.out.println("Quản lí nhân viên");
                    System.out.println("-----------------------");
                    NhanVienService nvs= new NhanVienService();
                    while (true) {
                        if (subChoise == 0) {
                            System.out.println("1: Danh sách nhân viên.");
                            System.out.println("2: Thêm nhân viên.");
                            System.out.println("3: Cập nhật nhân viên.");
                            System.out.println("4: Xoá nhân viên.");
                            System.out.println("5: Tìm nhân viên theo mã.");
                            System.out.println("6: Tìm nhân viên theo tên.");   
                            System.out.println("7: Tìm nhân viên theo chức vụ.");
                            System.out.println("-1: Trở lại");
                            System.out.print("Vui lòng nhập lựa chọn của bạn:");
                            subChoise = sc.nextInt();
                        } else if (subChoise == 1) {
                            System.out.println("Danh sách nhân viên.");
                            System.out.println("-----------------------");
                            List<NhanVienClass> danhSachNhanVien = new ArrayList<NhanVienClass>();
                            danhSachNhanVien = nvs.layDanhSachNhanVien(conn);
                            nvs.inDanhSachNhanVien(danhSachNhanVien);
                            subChoise = 0;
                        } else if (subChoise == 2) {
                            System.out.println("Thêm nhân viên.");
                            System.out.println("-----------------------");
                            sc.nextLine();
                            
                            System.out.print("Vui lòng nhập mã chuc vu muốn thêm:");
                            int maChucVuMoi = sc.nextInt();
                            ChucVuService cv =new ChucVuService();
                            if (!cv.coChucVu(conn, maChucVuMoi)) {
                                System.out.println("-----------------------");
                                System.out.println("Không tốn tại mã chuc vu");
                                System.out.println("-----------------------");
                                subChoise=0;
                                continue;
                            }
                            
                            System.out.print("Vui lòng nhập mã phong ban muốn thêm:");
                            int maPhongBanMoi = sc.nextInt();
                            PhongBanService pb =new PhongBanService();
                            if (!pb.coPhongBan(conn, maPhongBanMoi)) {
                                System.out.println("-----------------------");
                                System.out.println("Không tốn tại mã phòng ban");
                                System.out.println("-----------------------");
                                subChoise=0;
                                continue;
                            }
                            
                            sc.nextLine();
                            
                            System.out.print("Vui lòng nhập họ tên nhân viên muốn thêm:");
                            String tenNhanVienMoi = sc.nextLine();
                            
                            System.out.print("Vui lòng nhập địa chỉ nhân viên muốn thêm:");
                            String diaChiNhanVienMoi = sc.nextLine();
                            
                            System.out.print("Vui lòng nhập SĐT nhân viên muốn thêm:");
                            String sdtNhanVienMoi = sc.nextLine();
                            System.out.println("-----------------------");
                            nvs.themNhanVien(conn, maChucVuMoi, maPhongBanMoi, tenNhanVienMoi, diaChiNhanVienMoi, sdtNhanVienMoi);
                            subChoise = 0;
                        } else if (subChoise == 3) {
                            System.out.println("Cập nhật nhân viên.");
                            System.out.println("-----------------------");
                            sc.nextLine();
                            System.out.print("Vui lòng nhập mã nhân viên muốn sửa:");
                            int maNhanVienMoi = sc.nextInt();
                            System.out.print("Vui lòng nhập mã chức vụ muốn sửa:");
                            int maChucVuMoi = sc.nextInt();
                            System.out.print("Vui lòng nhập mã phong ban muốn sửa:");
                            int maPhongBanMoi = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Vui lòng nhập họ tên nhân viên muốn sửa:");
                            String tenNhanVienMoi = sc.nextLine();
                            System.out.print("Vui lòng nhập địa chỉ nhân viên muốn sửa:");
                            String diaChiNhanVienMoi = sc.nextLine();
                            System.out.print("Vui lòng nhập SĐT nhân viên muốn sửa:");
                            String sdtNhanVienMoi = sc.nextLine();
                            System.out.println("-----------------------");
                            nvs.capNhatNhanVien(conn, maNhanVienMoi, maChucVuMoi, maPhongBanMoi, tenNhanVienMoi, diaChiNhanVienMoi, sdtNhanVienMoi);
                            subChoise = 0;
                        } else if (subChoise == 4) {
                            System.out.println("Xoá nhân viên");
                            System.out.println("-----------------------");
                            System.out.print("Vui lòng nhập mã nhân viên muốn xoá:");
                            int maNhanVien = sc.nextInt();
                            System.out.println("-----------------------");
                            nvs.xoaNhanVien(conn, maNhanVien);
                                    
                            subChoise = 0;
                        } else if (subChoise == 5) {
                            System.out.println("Tim nhan viên theo mã nhân viên:");
                            System.out.println("Vui lòng nhập mã nhân viên bạn muốn tìm:");
                            int maNV = sc.nextInt();             
                            nvs.timNhanVienTheoMaNV(conn,maNV);
                            
                            subChoise = 0;
                        
                        } else if (subChoise == 6) {
                            System.out.println("Tim nhan viên theo tên nhân viên:");
                            System.out.println("Vui lòng nhập tên nhân viên bạn muốn tìm:");
                            sc.nextLine();
                            String tenNhanVien = sc.nextLine();             
                            nvs.timNhanVienTheoTenNV(conn,tenNhanVien);
                            
                            subChoise = 0;
                        
                        } else if (subChoise == 7) {
                            System.out.println("Tim nhan viên theo mã chức vụ nhân viên:");
                            int maCV = sc.nextInt();             
                            nvs.timNhanVienTheoMaChucVu(conn,maCV);
                            
                            subChoise = 0;
                        
                        } else if (subChoise == -1) {
                            subChoise = 0;
                            choise = 0;
                            break;
                        } else {
                            System.out.println("Không hợp lệ vui lòng nhập lại!");
                            subChoise = 0;
                        }
                    }
                } else if (choise == 2) {
                    System.out.println("Quản lí chức vụ");
                    System.out.println("-----------------------");
                    ChucVuService cvs = new ChucVuService();
                    while (true) {
                        if (subChoise == 0) {
                            System.out.println("1: Danh sách chức vụ.");
                            System.out.println("2: Thêm chức vụ");
                            System.out.println("3: Cập nhật chức vụ");
                            System.out.println("4: Xoá chức vụ");
                            System.out.println("-1: Trở lại");
                            System.out.print("Vui lòng nhập lựa chọn của bạn:");
                            subChoise = sc.nextInt();
                        } else if (subChoise == 1) {
                            System.out.println("Danh sách chức vụ.");
                            System.out.println("-----------------------");
                            List<ChucVuClass> danhSachChucVu = new ArrayList<ChucVuClass>();
                            danhSachChucVu = cvs.layDanhSachChucVu(conn);
                            cvs.inDanhSachChucVu(danhSachChucVu);
                            subChoise = 0;
                        } else if (subChoise == 2) {
                            System.out.println("Thêm chức vụ");
                            System.out.println("-----------------------");
                            sc.nextLine();
                            System.out.print("Vui lòng nhập tên chức vụ muốn thêm:");
                            String tenChucVuMoi = sc.nextLine();
                            System.out.println("-----------------------");
                            cvs.themChucVu(conn, tenChucVuMoi);
                            subChoise = 0;
                        } else if (subChoise == 3) {
                            System.out.println("Cập nhật chức vụ");
                            System.out.println("-----------------------");
                            System.out.print("Vui lòng nhập mã chức vụ muốn cập nhật:");
                            int maChucVu = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Vui lòng nhập tên chức vụ muốn cập nhật:");
                            String tenChucVu = sc.nextLine();
                            System.out.println("-----------------------");
                            cvs.capNhatChucVu(conn, maChucVu,tenChucVu);
                            subChoise = 0;
                        } else if (subChoise == 4) {
                            System.out.println("Xoá chức vụ");
                            System.out.println("-----------------------");
                            System.out.print("Vui lòng nhập mã chức vụ muốn xoá:");
                            int maChucVu = sc.nextInt();
                            System.out.println("-----------------------");
                            cvs.xoaChucVu(conn, maChucVu);
                            subChoise = 0;
                        } else if (subChoise == -1) {
                            subChoise = 0;
                            choise = 0;
                            break;
                        } else {
                            System.out.println("Không hợp lệ vui lòng nhập lại!");
                            subChoise = 0;
                        }
                    }
                } else if (choise == 3) {
                    System.out.println("Quản lí phòng ban");
                    System.out.println("-----------------------");
                    PhongBanService pbs = new PhongBanService();
                    while (true) {
                        if (subChoise == 0) {
                            System.out.println("1: Danh sách phòng ban.");
                            System.out.println("2: Thêm phòng ban");
                            System.out.println("3: Cập nhật phòng ban");
                            System.out.println("4: Xoá phòng ban");
                            System.out.println("-1: Trở lại");
                            System.out.print("Vui lòng nhập lựa chọn của bạn:");
                            subChoise = sc.nextInt();
                        } else if (subChoise == 1) {
                            System.out.println("Danh sách phòng ban.");
                            System.out.println("-----------------------");
                            List<PhongBanClass> danhSachPhongBan = new ArrayList<PhongBanClass>();
                            danhSachPhongBan = pbs.layDanhSachPhongBan(conn);
                            pbs.inDanhSachPhongBan(danhSachPhongBan);
                            subChoise = 0;
                        } else if (subChoise == 2) {
                            System.out.println("Thêm phòng ban");
                            System.out.println("-----------------------");
                            sc.nextLine();
                            System.out.print("Vui lòng nhập tên phòng ban muốn thêm:");
                            String tenPhongBanMoi = sc.nextLine();
                            System.out.println("-----------------------");
                            pbs.themPhongBan(conn, tenPhongBanMoi);
                            subChoise = 0;
                        } else if (subChoise == 3) {
                            System.out.println("Cập nhật phòng ban");
                            System.out.println("-----------------------");
                            System.out.print("Vui lòng nhập mã phòng ban muốn cập nhật:");
                            int maPhongBanMoi = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Vui lòng nhập tên phòng ban muốn cập nhật:");
                            String tenPhongBanMoi = sc.nextLine();
                            System.out.println("-----------------------");
                            pbs.capNhatPhongBan(conn, maPhongBanMoi,tenPhongBanMoi);
                            subChoise = 0;
                        } else if (subChoise == 4) {
                            System.out.println("Xoá phòng ban");
                            System.out.println("-----------------------");
                            System.out.print("Vui lòng nhập mã phòng ban muốn xoá:");
                            int maPhongBanMoi = sc.nextInt();
                            System.out.println("-----------------------");
                            pbs.xoaPhongBan(conn, maPhongBanMoi);
                            subChoise = 0;
                        } else if (subChoise == -1) {
                            subChoise = 0;
                            choise = 0;
                            break;
                        } else {
                            System.out.println("Không hợp lệ vui lòng nhập lại!");
                            subChoise = 0;
                        }
                    }
//                    
                } else if (choise == -1) {
                    break;
                } else {
                    System.out.println("Không hợp lệ vui lòng nhập lại!");
                    choise = 0;
                }

            }

        } catch (Exception ex) { //xử lý ngoại lệ nếu có
            System.out.println("Nối kết không thành công");
            ex.printStackTrace();
        }
    }
}

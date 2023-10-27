package repository;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.NhanVien;
import service.NhanVienInterface;

public class NhanVienRepository implements NhanVienInterface<NhanVien> {

    private Connection conn;

    public NhanVienRepository() {
        try {
            conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<NhanVien> getAll() {
        List<NhanVien> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM NhanVien";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                list.add(nhanVien);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void add(NhanVien nhanVien) {
        try {
            String query = "INSERT INTO NhanVien (MaNV,MatKhau,HoTen,VaiTro) VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, nhanVien.getMa());
            ps.setObject(2, nhanVien.getMatKhau());
            ps.setObject(3, nhanVien.getHoTen());
            ps.setObject(4, nhanVien.getVaiTro());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(NhanVien nhanVien) {
        try {
            String query = "UPDATE NhanVien SET MatKhau = ? ,HoTen = ? ,VaiTro = ? WHERE MaNV = ?"; 
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, nhanVien.getMatKhau());
            ps.setObject(2, nhanVien.getHoTen());
            ps.setObject(3, nhanVien.getVaiTro());
            ps.setObject(4, nhanVien.getMa());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int index) {
        try {
            String query = "DELETE FROM KhoaHoc WHERE MaNV = ? ;" + "DELETE FROM NguoiHoc WHERE MaNV = ? ;" + "DELETE FROM NhanVien WHERE  MaNV = ? ;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, index);
            ps.setInt(2, index);
            ps.setInt(3, index);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isMaNVExists(int maNV) {
        try {
            String query = "SELECT COUNT(*) FROM NhanVien WHERE MaNV = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, maNV);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Trả về true nếu mã nhân viên đã tồn tại, ngược lại trả về false.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu có lỗi xảy ra hoặc mã không tồn tại.
    }

    @Override
    public List<NhanVien> search(NhanVien nhanVien) {
        List<NhanVien> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM NhanVien WHERE HoTen like ? or HoTen like ? or HoTen like ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, nhanVien.getHoTen() + "%");
            ps.setObject(2, "%" + nhanVien.getHoTen() + "%");
            ps.setObject(3, "%" + nhanVien.getHoTen());
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                list.add(new NhanVien(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    @Override
    public List<NhanVien> sort() {
        List<NhanVien> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM NhanVien ORDER BY MaNV DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new NhanVien(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
}

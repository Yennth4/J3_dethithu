package service;

import java.util.List;
import model.NhanVien;
import repository.NhanVienRepository;

public class NhanVienService {

    private NhanVienRepository repository;

    public NhanVienService() {
        repository = new NhanVienRepository();
    }

    public List<NhanVien> getAll() {
        return repository.getAll();
    }

    public void add(NhanVien nhanVien) {
        repository.add(nhanVien);
    }

    public void remove(int index) {
        repository.remove(index);
    }

    public boolean isMaNVExists(int maNV) {
        return repository.isMaNVExists(maNV);
    }
}

package service;

import java.util.List;
import model.NhanVien;

public interface NhanVienInterface<T> {

    public List<T> getAll();

    public List<T> search(T entity);

    public void add(T entity);

    public void update(T entity);

    public void remove(int index);

    public boolean isMaNVExists(int maNV);
    
    public List<T> sort();

}

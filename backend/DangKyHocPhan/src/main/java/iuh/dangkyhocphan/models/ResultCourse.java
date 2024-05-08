package iuh.dangkyhocphan.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "resultCourses")
public class ResultCourse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resultCourse_id")
    private Long id;
    private double diemGiuaKi;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Double> diemThuongKi = new ArrayList<>();
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Double> diemThucHanh = new ArrayList<>();
    private double diemCuoiKi;
    private String ghiChu;
    private String trangThai;
    private double diemTongKet;

    public ResultCourse() {
    }

    public ResultCourse(double diemGiuaKi, List<Double> diemThuongKi, List<Double> diemThucHanh, double diemCuoiKi, String ghiChu, String trangThai, double diemTongKet) {
        this.diemGiuaKi = diemGiuaKi;
        this.diemThuongKi = diemThuongKi;
        this.diemThucHanh = diemThucHanh;
        this.diemCuoiKi = diemCuoiKi;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
        this.diemTongKet = diemTongKet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getDiemGiuaKi() {
        return diemGiuaKi;
    }

    public void setDiemGiuaKi(double diemGiuaKi) {
        this.diemGiuaKi = diemGiuaKi;
    }

    public List<Double> getDiemThuongKi() {
        return diemThuongKi;
    }

    public void setDiemThuongKi(List<Double> diemThuongKi) {
        this.diemThuongKi = diemThuongKi;
    }

    public List<Double> getDiemThucHanh() {
        return diemThucHanh;
    }

    public void setDiemThucHanh(List<Double> diemThucHanh) {
        this.diemThucHanh = diemThucHanh;
    }

    public double getDiemCuoiKi() {
        return diemCuoiKi;
    }

    public void setDiemCuoiKi(double diemCuoiKi) {
        this.diemCuoiKi = diemCuoiKi;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public double getDiemTongKet() {
        return diemTongKet;
    }

    public void setDiemTongKet(double diemTongKet) {
        this.diemTongKet = diemTongKet;
    }

    @Override
    public String toString() {
        return "ResultCourse{" +
                "id=" + id +
                ", diemGiuaKi=" + diemGiuaKi +
                ", diemThuongKi=" + diemThuongKi +
                ", diemThucHanh=" + diemThucHanh +
                ", diemCuoiKi=" + diemCuoiKi +
                ", ghiChu='" + ghiChu + '\'' +
                ", trangThai='" + trangThai + '\'' +
                ", diemTongKet=" + diemTongKet +
                '}';
    }
}

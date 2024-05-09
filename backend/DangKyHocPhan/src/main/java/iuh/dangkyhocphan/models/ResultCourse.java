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

    public String convertPointToChart() {
        if(this.getDiemTongKet() >= 0 && this.getDiemTongKet() < 4) return "F";
        else if(this.getDiemTongKet() >= 4 && this.getDiemTongKet() < 5) return "D";
        else if(this.getDiemTongKet() >= 5 && this.getDiemTongKet() < 5.5) return "D+";
        else if(this.getDiemTongKet() >= 5.5 && this.getDiemTongKet() < 6.0) return "C";
        else if(this.getDiemTongKet() >= 6.0 && this.getDiemTongKet() < 7) return "C+";
        else if(this.getDiemTongKet() >= 7 && this.getDiemTongKet() < 8) return "B";
        else if(this.getDiemTongKet() >= 8 && this.getDiemTongKet() < 8.5) return "B+";
        else if(this.getDiemTongKet() >= 8.5 && this.getDiemTongKet() < 9) return "A";
        else return "A+";

    }

    public Double convertToA4Point() {
        switch (this.convertPointToChart()) {
            case "F":
                return 0.0;
            case "D":
                return 1.0;
            case "D+":
                return 1.5;
            case "C":
                return 2.0;
            case "C+":
                return 2.5;
            case "B":
                return 3.0;
            case "B+":
                return 3.5;
            case "A":
                return 3.8;
            default:
                return 4.0;
        }
    }

    public String convertToRank() {
        switch (this.convertPointToChart()) {
            case "F":
                return "Kém";
            case "D":
                return "Trung Bình Yếu";
            case "D+":
                return "Trung Bình Yếu";
            case "C":
                return "Trung Bình";
            case "C+":
                return "Trung Bình";
            case "B":
                return "Khá";
            case "B+":
                return "Khá";
            case "A":
                return "Giỏi";
            default:
                return "Xuất sắc";
        }
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

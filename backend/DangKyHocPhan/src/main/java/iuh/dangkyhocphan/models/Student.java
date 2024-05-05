package iuh.dangkyhocphan.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("student")
public class Student extends User{
    private String bacDaoTao;
    private String loaiHinhDaoTao;
    private String chuyenNganh;
    private LocalDate ngayVaoTruong;
    private String lopHoc;
    private String nienKhoa;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> dsDangKy = new ArrayList<>();

    public Student() {
    }

    public Student(String hoTen, LocalDate ngaySinh, boolean gioiTinh, String cccd, String sdt, String email, String diaChi, String khoa, String trangThai, String bacDaoTao, String loaiHinhDaoTao, String chuyenNganh, LocalDate ngayVaoTruong, String lopHoc, String nienKhoa, List<Enrollment> dsDangKy) {
        super(hoTen, ngaySinh, gioiTinh, cccd, sdt, email, diaChi, khoa, trangThai);
        this.bacDaoTao = bacDaoTao;
        this.loaiHinhDaoTao = loaiHinhDaoTao;
        this.chuyenNganh = chuyenNganh;
        this.ngayVaoTruong = ngayVaoTruong;
        this.lopHoc = lopHoc;
        this.nienKhoa = nienKhoa;
        this.dsDangKy = dsDangKy;
    }

    public String getBacDaoTao() {
        return bacDaoTao;
    }

    public void setBacDaoTao(String bacDaoTao) {
        this.bacDaoTao = bacDaoTao;
    }

    public String getLoaiHinhDaoTao() {
        return loaiHinhDaoTao;
    }

    public void setLoaiHinhDaoTao(String loaiHinhDaoTao) {
        this.loaiHinhDaoTao = loaiHinhDaoTao;
    }

    public String getChuyenNganh() {
        return chuyenNganh;
    }

    public void setChuyenNganh(String chuyenNganh) {
        this.chuyenNganh = chuyenNganh;
    }

    public LocalDate getNgayVaoTruong() {
        return ngayVaoTruong;
    }

    public void setNgayVaoTruong(LocalDate ngayVaoTruong) {
        this.ngayVaoTruong = ngayVaoTruong;
    }

    public String getLopHoc() {
        return lopHoc;
    }

    public void setLopHoc(String lopHoc) {
        this.lopHoc = lopHoc;
    }

    public String getNienKhoa() {
        return nienKhoa;
    }

    public void setNienKhoa(String nienKhoa) {
        this.nienKhoa = nienKhoa;
    }

    public List<Enrollment> getDsDangKy() {
        return dsDangKy;
    }

    public void setDsDangKy(List<Enrollment> dsDangKy) {
        this.dsDangKy = dsDangKy;
    }

    @Override
    public String toString() {
        return "Student{" +
                "bacDaoTao='" + bacDaoTao + '\'' +
                ", loaiHinhDaoTao='" + loaiHinhDaoTao + '\'' +
                ", chuyenNganh='" + chuyenNganh + '\'' +
                ", ngayVaoTruong=" + ngayVaoTruong +
                ", lopHoc='" + lopHoc + '\'' +
                ", nienKhoa='" + nienKhoa + '\'' +
                ", dsDangKy=" + dsDangKy +
                ", id=" + id +
                ", hoTen='" + hoTen + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", gioiTinh=" + gioiTinh +
                ", cccd='" + cccd + '\'' +
                ", sdt='" + sdt + '\'' +
                ", email='" + email + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", khoa='" + khoa + '\'' +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}

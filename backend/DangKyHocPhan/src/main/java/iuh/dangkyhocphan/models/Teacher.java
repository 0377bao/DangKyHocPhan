package iuh.dangkyhocphan.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long id;
    private String hoTen;
    private LocalDate ngaySinh;
    private boolean gioiTinh;
    private String cccd;
    private String sdt;
    private String email;
    private String diaChi;
    private String khoa;
    private String chuyenNganh;
    private String trinhDoHocVan;
    private LocalDate ngayVaoTruong;
    private boolean trangThai;

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", hoTen='" + hoTen + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", gioiTinh=" + gioiTinh +
                ", cccd='" + cccd + '\'' +
                ", sdt='" + sdt + '\'' +
                ", email='" + email + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", khoa='" + khoa + '\'' +
                ", chuyenNganh='" + chuyenNganh + '\'' +
                ", trinhDoHocVan='" + trinhDoHocVan + '\'' +
                ", ngayVaoTruong=" + ngayVaoTruong +
                ", trangThai=" + trangThai +
                '}';
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public String getChuyenNganh() {
        return chuyenNganh;
    }

    public void setChuyenNganh(String chuyenNganh) {
        this.chuyenNganh = chuyenNganh;
    }

    public String getTrinhDoHocVan() {
        return trinhDoHocVan;
    }

    public void setTrinhDoHocVan(String trinhDoHocVan) {
        this.trinhDoHocVan = trinhDoHocVan;
    }

    public LocalDate getNgayVaoTruong() {
        return ngayVaoTruong;
    }

    public void setNgayVaoTruong(LocalDate ngayVaoTruong) {
        this.ngayVaoTruong = ngayVaoTruong;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Teacher() {
    }

    public Teacher(String hoTen, LocalDate ngaySinh, boolean gioiTinh, String cccd, String sdt, String email, String diaChi, String khoa, String chuyenNganh, String trinhDoHocVan, LocalDate ngayVaoTruong, boolean trangThai) {
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.cccd = cccd;
        this.sdt = sdt;
        this.email = email;
        this.diaChi = diaChi;
        this.khoa = khoa;
        this.chuyenNganh = chuyenNganh;
        this.trinhDoHocVan = trinhDoHocVan;
        this.ngayVaoTruong = ngayVaoTruong;
        this.trangThai = trangThai;
    }
}

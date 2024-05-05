package iuh.dangkyhocphan.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;
    private String tenMonHoc;
    private int soTinChi;
    @ManyToMany
    private List<Course> monTienQuyet;
    private int hocPhi;
    @ElementCollection
    private List<String> khoa;
    @ElementCollection
    private List<String> chuyenNganh;

    public Course(String tenMonHoc, int soTinChi, List<Course> monTienQuyet, int hocPhi, List<String> khoa, List<String> chuyenNganh) {
        this.tenMonHoc = tenMonHoc;
        this.soTinChi = soTinChi;
        this.monTienQuyet = monTienQuyet;
        this.hocPhi = hocPhi;
        this.khoa = khoa;
        this.chuyenNganh = chuyenNganh;
    }

    public Course() {
    }

    public Long getId() {
        return id;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", tenMonHoc='" + tenMonHoc + '\'' +
                ", soTinChi=" + soTinChi +
                ", monTienQuyet=" + monTienQuyet +
                ", hocPhi=" + hocPhi +
                ", khoa=" + khoa +
                ", chuyenNganh=" + chuyenNganh +
                '}';
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }

    public List<Course> getMonTienQuyet() {
        return monTienQuyet;
    }

    public void setMonTienQuyet(List<Course> monTienQuyet) {
        this.monTienQuyet = monTienQuyet;
    }

    public int getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(int hocPhi) {
        this.hocPhi = hocPhi;
    }

    public List<String> getKhoa() {
        return khoa;
    }

    public void setKhoa(List<String> khoa) {
        this.khoa = khoa;
    }

    public List<String> getChuyenNganh() {
        return chuyenNganh;
    }

    public void setChuyenNganh(List<String> chuyenNganh) {
        this.chuyenNganh = chuyenNganh;
    }
}

package iuh.dangkyhocphan.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;
    @Column(unique = true, nullable = false, length = 300)
    private String tenMonHoc;
    private int soTinChi;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Course> monTienQuyet;
    private int hocPhi;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> khoa;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> chuyenNganh;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_opening_id")
    private CourseOpening courseOpening;


    public Course() {
    }

    public Course(String tenMonHoc, int soTinChi, List<Course> monTienQuyet, int hocPhi, List<String> khoa, List<String> chuyenNganh, CourseOpening courseOpening) {
        this.tenMonHoc = tenMonHoc;
        this.soTinChi = soTinChi;
        this.monTienQuyet = monTienQuyet;
        this.hocPhi = hocPhi;
        this.khoa = khoa;
        this.chuyenNganh = chuyenNganh;
        this.courseOpening = courseOpening;
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

    public CourseOpening getCourseOpening() {
        return courseOpening;
    }

    public void setCourseOpening(CourseOpening courseOpening) {
        this.courseOpening = courseOpening;
    }
}

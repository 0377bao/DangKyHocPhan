package iuh.dangkyhocphan.models;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "classes")
public class Clazz implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long id;
    private String tenLop;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    private Teacher giangVien;
    private int siSoHienTai;
    private int siSoToiDa;
    private String trangThai;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "administrator_id")
    private Administrator administrator;

    @Override
    public String toString() {
        return "Clazz{" +
                "id=" + id +
                ", tenLop='" + tenLop + '\'' +
                ", giangVien=" + giangVien +
                ", siSoHienTai=" + siSoHienTai +
                ", siSoToiDa=" + siSoToiDa +
                ", trangThai='" + trangThai + '\'' +
                ", ngayBatDau=" + ngayBatDau +
                ", ngayKetThuc=" + ngayKetThuc +
                ", course=" + course +
                ", administrator=" + administrator +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public Teacher getGiangVien() {
        return giangVien;
    }

    public void setGiangVien(Teacher giangVien) {
        this.giangVien = giangVien;
    }

    public int getSiSoHienTai() {
        return siSoHienTai;
    }

    public void setSiSoHienTai(int siSoHienTai) {
        this.siSoHienTai = siSoHienTai;
    }

    public int getSiSoToiDa() {
        return siSoToiDa;
    }

    public void setSiSoToiDa(int siSoToiDa) {
        this.siSoToiDa = siSoToiDa;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public Clazz() {
    }

    public Clazz(String tenLop, Teacher giangVien, int siSoHienTai, int siSoToiDa, String trangThai, LocalDate ngayBatDau, LocalDate ngayKetThuc, Course course, Administrator administrator) {
        this.tenLop = tenLop;
        this.giangVien = giangVien;
        this.siSoHienTai = siSoHienTai;
        this.siSoToiDa = siSoToiDa;
        this.trangThai = trangThai;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.course = course;
        this.administrator = administrator;
    }
}

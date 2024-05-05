package iuh.dangkyhocphan.models;

import jakarta.persistence.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "enrollments")
public class Enrollment {
    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "class_id")
    private Clazz clazz;

    @OneToOne(mappedBy = "id")
    private ResultCourse resultCourse;

    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private String hocKi;

    public Enrollment() {
    }

    public Enrollment(Student student, Clazz clazz, ResultCourse resultCourse, LocalDate ngayBatDau, LocalDate ngayKetThuc, String hocKi) {
        this.student = student;
        this.clazz = clazz;
        this.resultCourse = resultCourse;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.hocKi = hocKi;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public ResultCourse getResultCourse() {
        return resultCourse;
    }

    public void setResultCourse(ResultCourse resultCourse) {
        this.resultCourse = resultCourse;
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

    public String getHocKi() {
        return hocKi;
    }

    public void setHocKi(String hocKi) {
        this.hocKi = hocKi;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "student=" + student +
                ", clazz=" + clazz +
                ", resultCourse=" + resultCourse +
                ", ngayBatDau=" + ngayBatDau +
                ", ngayKetThuc=" + ngayKetThuc +
                ", hocKi='" + hocKi + '\'' +
                '}';
    }
}

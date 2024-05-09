package iuh.dangkyhocphan.models;


import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@IdClass(EnrollmentId.class)
@Table(name = "enrollments")
public class Enrollment implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "student_id")
    private Student student;

    @Id
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "class_id")
    private Clazz clazz;

    @OneToOne
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(student, that.student) && Objects.equals(clazz, that.clazz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, clazz);
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

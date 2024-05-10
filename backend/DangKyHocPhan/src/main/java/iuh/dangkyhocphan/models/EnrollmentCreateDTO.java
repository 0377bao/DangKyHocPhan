package iuh.dangkyhocphan.models;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class EnrollmentCreateDTO {
    private Long student_id;
    private Long class_id;
    private String hocKi;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;

    public EnrollmentCreateDTO(Long student_id, Long class_id, String hocKi, LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        this.student_id = student_id;
        this.class_id = class_id;
        this.hocKi = hocKi;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    @Override
    public String toString() {
        return "EnrollmentCreateDTO{" +
                "student_id=" + student_id +
                ", class_id=" + class_id +
                ", hocKi='" + hocKi + '\'' +
                ", ngayBatDau=" + ngayBatDau +
                ", ngayKetThuc=" + ngayKetThuc +
                '}';
    }

    public Long getClass_id() {
        return class_id;
    }

    public void setClass_id(Long class_id) {
        this.class_id = class_id;
    }

    public String getHocKi() {
        return hocKi;
    }

    public void setHocKi(String hocKi) {
        this.hocKi = hocKi;
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
}

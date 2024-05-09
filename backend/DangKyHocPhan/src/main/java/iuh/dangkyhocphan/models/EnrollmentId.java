package iuh.dangkyhocphan.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.time.LocalDate;


public class EnrollmentId implements Serializable {
    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "student_id")
    private Student student;
    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "class_id")
    private Clazz clazz;
    @Id
    private LocalDate ngayBatDau;
    @Id
    private LocalDate ngayKetThuc;
    @Id
    private String hocKi;
}

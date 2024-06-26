package iuh.dangkyhocphan.models;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "schedules")
public class Schedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    private Day thu;
    private String loaiLich;
    private String phongHoc;
    private String tietHoc;
    private String ghiChu;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id")
    private Clazz clazz;

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", thu=" + thu +
                ", loaiLich='" + loaiLich + '\'' +
                ", phongHoc='" + phongHoc + '\'' +
                ", tietHoc='" + tietHoc + '\'' +
                ", ghiChu='" + ghiChu + '\'' +
                ", clazz=" + clazz +
                '}';
    }

    public Long getId() {
        return id;
    }

    public Day getThu() {
        return thu;
    }

    public void setThu(Day thu) {
        this.thu = thu;
    }

    public String getLoaiLich() {
        return loaiLich;
    }

    public void setLoaiLich(String loaiLich) {
        this.loaiLich = loaiLich;
    }

    public String getPhongHoc() {
        return phongHoc;
    }

    public void setPhongHoc(String phongHoc) {
        this.phongHoc = phongHoc;
    }

    public String getTietHoc() {
        return tietHoc;
    }

    public void setTietHoc(String tietHoc) {
        this.tietHoc = tietHoc;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public Schedule() {
    }

    public Schedule(Day thu, String loaiLich, String phongHoc, String tietHoc, String ghiChu, Clazz clazz) {
        this.thu = thu;
        this.loaiLich = loaiLich;
        this.phongHoc = phongHoc;
        this.tietHoc = tietHoc;
        this.ghiChu = ghiChu;
        this.clazz = clazz;
    }
}

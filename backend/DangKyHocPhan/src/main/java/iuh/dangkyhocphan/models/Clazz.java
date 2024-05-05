package iuh.dangkyhocphan.models;
import jakarta.persistence.*;

@Entity
@Table(name = "classes")
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long id;
    private String tenLop;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher giangVien;
    private int siSoHienTai;
    private int siSoToiDa;
    private String trangThai;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;
    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
    @ManyToOne(fetch = FetchType.LAZY)
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
                ", course=" + course +
                ", schedule=" + schedule +
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

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public Clazz() {
    }

    public Clazz(String tenLop, Teacher giangVien, int siSoHienTai, int siSoToiDa, String trangThai, Course course, Schedule schedule, Administrator administrator) {
        this.tenLop = tenLop;
        this.giangVien = giangVien;
        this.siSoHienTai = siSoHienTai;
        this.siSoToiDa = siSoToiDa;
        this.trangThai = trangThai;
        this.course = course;
        this.schedule = schedule;
        this.administrator = administrator;
    }
}

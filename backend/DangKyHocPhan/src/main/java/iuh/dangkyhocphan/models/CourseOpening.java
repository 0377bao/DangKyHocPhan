package iuh.dangkyhocphan.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courseOpenings")
public class CourseOpening implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_opening_id")
    private Long id;

    private String hocky;

    @OneToMany(mappedBy = "courseOpening")
    private List<Course> dsKhoaHoc = new ArrayList<>();

    public CourseOpening() {
    }

    public CourseOpening(String hocky, List<Course> dsKhoaHoc) {
        this.hocky = hocky;
        this.dsKhoaHoc = dsKhoaHoc;
    }

    public Long getId() {
        return id;
    }

    public List<Course> getDsKhoaHoc() {
        return dsKhoaHoc;
    }

    public void setDsKhoaHoc(List<Course> dsKhoaHoc) {
        this.dsKhoaHoc = dsKhoaHoc;
    }

    public String getHocky() {
        return hocky;
    }

    public void setHocky(String hocky) {
        this.hocky = hocky;
    }

    @Override
    public String toString() {
        return "CourseOpening{" +
                "id=" + id +
                ", hocky='" + hocky + '\'' +
                ", dsKhoaHoc=" + dsKhoaHoc +
                '}';
    }
}

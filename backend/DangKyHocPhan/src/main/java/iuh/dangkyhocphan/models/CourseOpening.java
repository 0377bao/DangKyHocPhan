package iuh.dangkyhocphan.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courseOpenings")
public class CourseOpening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Course> dsKhoaHoc = new ArrayList<>();

    public CourseOpening() {
    }

    public CourseOpening(List<Course> dsKhoaHoc) {
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

    @Override
    public String toString() {
        return "CourseOpening{" +
                "id=" + id +
                ", dsKhoaHoc=" + dsKhoaHoc +
                '}';
    }
}

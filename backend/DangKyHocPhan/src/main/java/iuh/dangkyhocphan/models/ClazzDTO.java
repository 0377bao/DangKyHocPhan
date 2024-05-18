package iuh.dangkyhocphan.models;

import java.time.LocalDate;
import java.util.List;

public class ClazzDTO {
    private Long administratorId;
    private Long semesterId;
    private Long classId;
    private String tenLop;
    private Long courseId;
    private String tenMonHoc;
    private int soTinChi;
    private Long teacherId;
    private String tenGiangVien;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private List<ScheduleDTO> lichHocLyThuyet;
    private List<ScheduleDTO> lichHocThucHanh;
    private int siSoHienTai;
    private int siSoToiDa;
    private String trangThai;


    public ClazzDTO() {
    }

    public ClazzDTO(Long administratorId, Long semesterId, Long classId, String tenLop, Long courseId, String tenMonHoc, int soTinChi, Long teacherId, String tenGiangVien, LocalDate ngayBatDau, LocalDate ngayKetThuc, List<ScheduleDTO> lichHocLyThuyet, List<ScheduleDTO> lichHocThucHanh, int siSoHienTai, int siSoToiDa, String trangThai) {
        this.administratorId = administratorId;
        this.semesterId = semesterId;
        this.classId = classId;
        this.tenLop = tenLop;
        this.courseId = courseId;
        this.tenMonHoc = tenMonHoc;
        this.soTinChi = soTinChi;
        this.teacherId = teacherId;
        this.tenGiangVien = tenGiangVien;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.lichHocLyThuyet = lichHocLyThuyet;
        this.lichHocThucHanh = lichHocThucHanh;
        this.siSoHienTai = siSoHienTai;
        this.siSoToiDa = siSoToiDa;
        this.trangThai = trangThai;
    }

    public Long getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Long administratorId) {
        this.administratorId = administratorId;
    }

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
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

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTenGiangVien() {
        return tenGiangVien;
    }

    public void setTenGiangVien(String tenGiangVien) {
        this.tenGiangVien = tenGiangVien;
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

    public List<ScheduleDTO> getLichHocLyThuyet() {
        return lichHocLyThuyet;
    }

    public void setLichHocLyThuyet(List<ScheduleDTO> lichHocLyThuyet) {
        this.lichHocLyThuyet = lichHocLyThuyet;
    }

    public List<ScheduleDTO> getLichHocThucHanh() {
        return lichHocThucHanh;
    }

    public void setLichHocThucHanh(List<ScheduleDTO> lichHocThucHanh) {
        this.lichHocThucHanh = lichHocThucHanh;
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

    @Override
    public String toString() {
        return "ClazzDTO{" +
                "administratorId=" + administratorId +
                ", semesterId=" + semesterId +
                ", classId=" + classId +
                ", tenLop='" + tenLop + '\'' +
                ", courseId=" + courseId +
                ", tenMonHoc='" + tenMonHoc + '\'' +
                ", soTinChi=" + soTinChi +
                ", teacherId=" + teacherId +
                ", tenGiangVien='" + tenGiangVien + '\'' +
                ", ngayBatDau='" + ngayBatDau + '\'' +
                ", ngayKetThuc='" + ngayKetThuc + '\'' +
                ", lichHocLyThuyet=" + lichHocLyThuyet +
                ", lichHocThucHanh=" + lichHocThucHanh +
                ", siSoHienTai=" + siSoHienTai +
                ", siSoToiDa=" + siSoToiDa +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}

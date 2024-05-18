package iuh.dangkyhocphan.models;

public class ScheduleDTO {
    private Day thu;
    private String tietHoc;
    private String loaiLich;
    private String phongHoc;
    private String ghiChu;

    public ScheduleDTO() {
    }

    public ScheduleDTO(Day thu, String tietHoc, String loaiLich, String phongHoc, String ghiChu) {
        this.thu = thu;
        this.tietHoc = tietHoc;
        this.loaiLich = loaiLich;
        this.phongHoc = phongHoc;
        this.ghiChu = ghiChu;
    }

    public Day getThu() {
        return thu;
    }

    public void setThu(Day thu) {
        this.thu = thu;
    }

    public String getTietHoc() {
        return tietHoc;
    }

    public void setTietHoc(String tietHoc) {
        this.tietHoc = tietHoc;
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

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "ScheduleDTO{" +
                "thu='" + thu + '\'' +
                ", tietHoc='" + tietHoc + '\'' +
                ", loaiLich='" + loaiLich + '\'' +
                ", phongHoc='" + phongHoc + '\'' +
                ", ghiChu='" + ghiChu + '\'' +
                '}';
    }
}

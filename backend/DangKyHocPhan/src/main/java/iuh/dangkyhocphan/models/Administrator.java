package iuh.dangkyhocphan.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("administrator")
public class Administrator extends User{
    private int soNamCongTac;

    public Administrator(){}

    public Administrator(String hoTen, LocalDate ngaySinh, boolean gioiTinh, String cccd, String sdt, String email, String diaChi, String khoa, String trangThai, int soNamCongTac) {
        super(hoTen, ngaySinh, gioiTinh, cccd, sdt, email, diaChi, khoa, trangThai);
        this.soNamCongTac = soNamCongTac;
    }

    public int getSoNamCongTac() {
        return soNamCongTac;
    }

    public void setSoNamCongTac(int soNamCongTac) {
        this.soNamCongTac = soNamCongTac;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "soNamCongTac=" + soNamCongTac +
                ", id=" + id +
                ", hoTen='" + hoTen + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", gioiTinh=" + gioiTinh +
                ", cccd='" + cccd + '\'' +
                ", sdt='" + sdt + '\'' +
                ", email='" + email + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", khoa='" + khoa + '\'' +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}

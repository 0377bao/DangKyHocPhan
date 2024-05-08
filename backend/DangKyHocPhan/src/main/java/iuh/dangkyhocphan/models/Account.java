package iuh.dangkyhocphan.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "accounts")
public class Account implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "tenTaiKhoan")
    private User user;
    private String matKhau;
    private String vaiTro;

    public Account() {
    }

    public Account(User user, String matKhau, String vaiTro) {
        this.user = user;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    @Override
    public String toString() {
        return "Account{" +
                "user=" + user +
                ", matKhau='" + matKhau + '\'' +
                ", vaiTro='" + vaiTro + '\'' +
                '}';
    }

}

package iuh.dangkyhocphan.models;

import java.io.Serializable;
import java.util.Objects;

public class AccountId implements Serializable {
    private User user;
    private String matKhau;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountId accountId)) return false;
        return Objects.equals(user, accountId.user) && Objects.equals(matKhau, accountId.matKhau);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, matKhau);
    }
}

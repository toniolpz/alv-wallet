package alv.wallet.entity.type;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

// Embeddable class to handle Composite Primary Key
@Embeddable
public class BalanceId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "currency")
    private String currency;

    public BalanceId() {
    }

    public BalanceId(int userId, String currency) {
        this.userId = userId;
        this.currency = currency;
    }

    public int getUserId() {
        return userId;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof BalanceId))
            return false;
        BalanceId that = (BalanceId) o;
        return Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getCurrency(), that.getCurrency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getCurrency());
    }
}
package alv.wallet.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import alv.wallet.entity.type.BalanceId;

@Entity
@Table(name = "balance")
public class Balance {

    @EmbeddedId
    private BalanceId id;

    private double amount;

    public BalanceId getId() {
        return id;
    }

    public void setId(BalanceId id) {
        this.id = id;
    }

    public double getAmout() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
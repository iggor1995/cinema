package com.epam.igor.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_accounts")
public class UserAccount extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -2692253546761426748L;

    @Column(name = "user_id")
    private long userId;
    @Column(name = "cash")
    private double cash;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }
}

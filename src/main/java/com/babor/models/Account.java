package com.babor.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Formula;

@Entity(name = "Account")
public class Account {

    @Id
    private Long id;

    private Double credit;

    private Double rate;

    @Formula(value = "credit * rate")
    private Double interest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }
}

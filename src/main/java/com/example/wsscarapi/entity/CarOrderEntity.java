package com.example.wsscarapi.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "car_order", schema = "wss_car", catalog = "")
public class CarOrderEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "buyer")
    private String buyer;
    @Basic
    @Column(name = "seller")
    private String seller;
    @Basic
    @Column(name = "created_datetime")
    private Timestamp createdDatetime;
    @Basic
    @Column(name = "car")
    private String car;
    @Basic
    @Column(name = "completed")
    private byte completed;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public Timestamp getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Timestamp createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public byte getCompleted() {
        return completed;
    }

    public void setCompleted(byte completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarOrderEntity that = (CarOrderEntity) o;
        return completed == that.completed && Objects.equals(id, that.id) && Objects.equals(buyer, that.buyer) && Objects.equals(seller, that.seller) && Objects.equals(createdDatetime, that.createdDatetime) && Objects.equals(car, that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, buyer, seller, createdDatetime, car, completed);
    }
}

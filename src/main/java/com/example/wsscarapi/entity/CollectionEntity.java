package com.example.wsscarapi.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "collection", schema = "wss_car", catalog = "")
public class CollectionEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "created_datetime")
    private Timestamp createdDatetime;
    @Basic
    @Column(name = "by_user")
    private String byUser;
    @Basic
    @Column(name = "car")
    private String car;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Timestamp createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public String getByUser() {
        return byUser;
    }

    public void setByUser(String byUser) {
        this.byUser = byUser;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectionEntity that = (CollectionEntity) o;
        return id.equals(that.id)  && Objects.equals(createdDatetime, that.createdDatetime) && Objects.equals(byUser, that.byUser) && car.equals(that.car) ;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(createdDatetime, byUser);
        result = 31 * result + id.hashCode();
        result = 31 * result + car.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CollectionEntity{" +
                "id=" + id +
                ", createdDatetime=" + createdDatetime +
                ", byUser='" + byUser + '\'' +
                ", car=" + car +
                '}';
    }
}

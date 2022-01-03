package com.example.wsscarapi.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "comment", schema = "wss_car", catalog = "")
public class CommentEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "from_user")
    private String fromUser;
    @Basic
    @Column(name = "car")
    private String car;
    @Basic
    @Column(name = "parent")
    private String parent;
    @Basic
    @Column(name = "content")
    private String content;
    @Basic
    @Column(name = "created_datetime")
    private Timestamp createdDatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Timestamp createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;
        return id.equals(that.id) && Objects.equals(fromUser, that.fromUser) && car.equals(that.car)  && parent.equals(that.parent) && Objects.equals(content, that.content) && Objects.equals(createdDatetime, that.createdDatetime);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(fromUser, content, createdDatetime);
        result = 31 * result + id.hashCode();
        result = 31 * result + car.hashCode();
        result = 31 * result + parent.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
                "id=" + id +
                ", fromUser='" + fromUser + '\'' +
                ", car=" + car +
                ", parent=" + parent +
                ", content='" + content + '\'' +
                ", createdDatetime=" + createdDatetime +
                '}';
    }
}

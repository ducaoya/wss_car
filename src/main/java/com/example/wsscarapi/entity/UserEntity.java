package com.example.wsscarapi.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "wss_car", catalog = "")
public class UserEntity {
    @Id
    @Column(name = "username")
    private String username;

    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "profile_image")
    private String profileImage;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(username, that.username) && Objects.equals(phone, that.phone) && Objects.equals(password, that.password) && Objects.equals(profileImage, that.profileImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, phone, password, profileImage);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", profileImage='" + profileImage + '\'' +
                '}';
    }
}

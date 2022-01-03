package com.example.wsscarapi.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "car", schema = "wss_car", catalog = "")
public class CarEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "brand")
    private String brand;
    @Basic
    @Column(name = "series")
    private String series;
    @Basic
    @Column(name = "first_registered")
    private Date firstRegistered;
    @Basic
    @Column(name = "mileage")
    private String mileage;
    @Basic
    @Column(name = "emission_standard")
    private String emissionStandard;
    @Basic
    @Column(name = "number_of_assignments")
    private int numberOfAssignments;
    @Basic
    @Column(name = "emission")
    private String emission;
    @Basic
    @Column(name = "gearbox")
    private String gearbox;
    @Basic
    @Column(name = "color")
    private String color;
    @Basic
    @Column(name = "production_date")
    private Date productionDate;
    @Basic
    @Column(name = "number_of_keys")
    private int numberOfKeys;
    @Basic
    @Column(name = "price")
    private int price;
    @Basic
    @Column(name = "is_sold")
    private byte isSold;
    @Basic
    @Column(name = "is_deleted")
    private byte isDeleted;
    @Basic
    @Column(name = "from_user")
    private String fromUser;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "preview_image")
    private String previewImage;
    @Basic
    @Column(name = "images")
    private String images;
    @Basic
    @Column(name = "guazi_clue_id")
    private String guaziClueId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Date getFirstRegistered() {
        return firstRegistered;
    }

    public void setFirstRegistered(Date firstRegistered) {
        this.firstRegistered = firstRegistered;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getEmissionStandard() {
        return emissionStandard;
    }

    public void setEmissionStandard(String emissionStandard) {
        this.emissionStandard = emissionStandard;
    }

    public int getNumberOfAssignments() {
        return numberOfAssignments;
    }

    public void setNumberOfAssignments(int numberOfAssignments) {
        this.numberOfAssignments = numberOfAssignments;
    }

    public String getEmission() {
        return emission;
    }

    public void setEmission(String emission) {
        this.emission = emission;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public int getNumberOfKeys() {
        return numberOfKeys;
    }

    public void setNumberOfKeys(int numberOfKeys) {
        this.numberOfKeys = numberOfKeys;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte getIsSold() {
        return isSold;
    }

    public void setIsSold(byte isSold) {
        this.isSold = isSold;
    }

    public byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPreviewImage() {
        return previewImage;
    }

    public void setPreviewImage(String previewImage) {
        this.previewImage = previewImage;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getGuaziClueId() {
        return guaziClueId;
    }

    public void setGuaziClueId(String guaziClueId) {
        this.guaziClueId = guaziClueId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarEntity car = (CarEntity) o;
        return numberOfAssignments == car.numberOfAssignments && numberOfKeys == car.numberOfKeys && price == car.price && isSold == car.isSold && isDeleted == car.isDeleted && Objects.equals(id, car.id) && Objects.equals(brand, car.brand) && Objects.equals(series, car.series) && Objects.equals(firstRegistered, car.firstRegistered) && Objects.equals(mileage, car.mileage) && Objects.equals(emissionStandard, car.emissionStandard) && Objects.equals(emission, car.emission) && Objects.equals(gearbox, car.gearbox) && Objects.equals(color, car.color) && Objects.equals(productionDate, car.productionDate) && Objects.equals(fromUser, car.fromUser) && Objects.equals(title, car.title) && Objects.equals(previewImage, car.previewImage) && Objects.equals(images, car.images) && Objects.equals(guaziClueId, car.guaziClueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, series, firstRegistered, mileage, emissionStandard, numberOfAssignments, emission, gearbox, color, productionDate, numberOfKeys, price, isSold, isDeleted, fromUser, title, previewImage, images, guaziClueId);
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", series='" + series + '\'' +
                ", firstRegistered=" + firstRegistered +
                ", mileage='" + mileage + '\'' +
                ", emissionStandard='" + emissionStandard + '\'' +
                ", numberOfAssignments=" + numberOfAssignments +
                ", emission='" + emission + '\'' +
                ", gearbox='" + gearbox + '\'' +
                ", color='" + color + '\'' +
                ", productionDate=" + productionDate +
                ", numberOfKeys=" + numberOfKeys +
                ", price=" + price +
                ", isSold=" + isSold +
                ", isDeleted=" + isDeleted +
                ", fromUser='" + fromUser + '\'' +
                ", title='" + title + '\'' +
                ", previewImage='" + previewImage + '\'' +
                ", images='" + images + '\'' +
                ", guaziClueId='" + guaziClueId + '\'' +
                '}';
    }
}

package com.example.wsscarapi.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "delivery", schema = "wss_car", catalog = "")
public class DeliveryEntity {
    @Id
    @Column(name = "car_order")
    private String carOrder;
    @Basic
    @Column(name = "message_from_seller")
    private String messageFromSeller;
    @Basic
    @Column(name = "seller_contact")
    private String sellerContact;
    @Basic
    @Column(name = "message_from_buyer")
    private String messageFromBuyer;
    @Basic
    @Column(name = "buyer_contact")
    private String buyerContact;

    public String getCarOrder() {
        return carOrder;
    }

    public void setCarOrder(String carOrder) {
        this.carOrder = carOrder;
    }

    public String getMessageFromSeller() {
        return messageFromSeller;
    }

    public void setMessageFromSeller(String messageFromSeller) {
        this.messageFromSeller = messageFromSeller;
    }

    public String getSellerContact() {
        return sellerContact;
    }

    public void setSellerContact(String sellerContact) {
        this.sellerContact = sellerContact;
    }

    public String getMessageFromBuyer() {
        return messageFromBuyer;
    }

    public void setMessageFromBuyer(String messageFromBuyer) {
        this.messageFromBuyer = messageFromBuyer;
    }

    public String getBuyerContact() {
        return buyerContact;
    }

    public void setBuyerContact(String buyerContact) {
        this.buyerContact = buyerContact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryEntity that = (DeliveryEntity) o;
        return Objects.equals(carOrder, that.carOrder) && Objects.equals(messageFromSeller, that.messageFromSeller) && Objects.equals(sellerContact, that.sellerContact) && Objects.equals(messageFromBuyer, that.messageFromBuyer) && Objects.equals(buyerContact, that.buyerContact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carOrder, messageFromSeller, sellerContact, messageFromBuyer, buyerContact);
    }

    @Override
    public String toString() {
        return "DeliveryEntity{" +
                "carOrder='" + carOrder + '\'' +
                ", messageFromSeller='" + messageFromSeller + '\'' +
                ", sellerContact='" + sellerContact + '\'' +
                ", messageFromBuyer='" + messageFromBuyer + '\'' +
                ", buyerContact='" + buyerContact + '\'' +
                '}';
    }
}

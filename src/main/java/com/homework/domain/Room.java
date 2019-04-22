package com.homework.domain;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Objects;

@Entity
@Table(name = "rooms")
public class Room {

    public enum Category {
        PRESIDENT, DUPLEX, APARTMENT, FAMILY_ROOM, STD


    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "number")
    private long number;
    @Column(name = "category")
    private Category category;
    @Column(name = "price")
    private long price;
    @Column(name = "reserved")
    private boolean reserved;

    public Room() {
    }

    public Room(long number, Category category, long price, boolean reserved) {
        this.number = number;
        this.category = category;
        this.price = price;
        this.reserved = reserved;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id &&
                number == room.number &&
                price == room.price &&
                reserved == room.reserved &&
                category == room.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, category, price, reserved);
    }

    @Override
    public String toString() {
        return "com.homework.domain.Room{" +
                "id=" + id +
                ", number=" + number +
                ", category=" + category +
                ", price=" + price +
                ", reserved=" + reserved +
                '}';
    }
}

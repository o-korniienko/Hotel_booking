package com.homework.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.DAYS;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long total_price;
    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDate bookingFrom;
    private LocalDate bookingUntil;
    private long duration;
    @ManyToMany
    private List<AdditionalOption> options;

    public Booking() {
    }

    public Booking(User user, Room room, LocalDate bookingFrom, LocalDate bookingUntil) {
        this.room = room;
        this.user = user;
        this.bookingFrom = bookingFrom;
        this.bookingUntil = bookingUntil;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTotal_price() {
        return total_price;
    }

    public void setDurationAndTotalPrice() {
        this.duration = duration();
        this.total_price = totalPrice();
    }

    public long totalPrice() {
        this.duration = duration();
        long optionsPrice = 0;
        long roomPrice = room.getPrice();
        if (options.size() == 0) {
            return roomPrice * duration;
        } else {
            for (AdditionalOption option : options) {
                optionsPrice += option.getOption_price();
            }
        }
        return roomPrice * duration + optionsPrice;
    }

    public long duration() {
        return DAYS.between(bookingFrom, bookingUntil);
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getBookingFrom() {
        return bookingFrom;
    }

    public void setBookingFrom(LocalDate bookingFrom) {
        this.bookingFrom = bookingFrom;
    }

    public LocalDate getBookingUntil() {
        return bookingUntil;
    }

    public void setBookingUntil(LocalDate bookingUntil) {
        this.bookingUntil = bookingUntil;
    }

    public void setTotal_price(long total_price) {
        this.total_price = total_price;
    }

    public List<AdditionalOption> getOptions() {
        return options;
    }

    public void setOptions(List<AdditionalOption> options) {
        this.options = options;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id == booking.id &&
                total_price == booking.total_price &&
                duration == booking.duration &&
                Objects.equals(room, booking.room) &&
                Objects.equals(user, booking.user) &&
                Objects.equals(bookingFrom, booking.bookingFrom) &&
                Objects.equals(bookingUntil, booking.bookingUntil) &&
                Objects.equals(options, booking.options);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, total_price, room, user, bookingFrom, bookingUntil, duration, options);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", total_price=" + total_price +
                ", room=" + room +
                ", user=" + user +
                ", bookingFrom=" + bookingFrom +
                ", bookingUntil=" + bookingUntil +
                ", duration=" + duration +
                '}';
    }
}

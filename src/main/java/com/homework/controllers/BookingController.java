package com.homework.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.homework.domain.*;
import com.homework.repositories.BookingRepository;
import com.homework.repositories.RoomRepository;
import com.homework.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping(path = "/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;


    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GetMapping
    public Iterable<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @PostMapping
    public Booking
    createBooking(@RequestBody String bookingJson) {
        Booking booking = gson.fromJson(bookingJson, Booking.class);
        booking.setDurationAndTotalPrice();
        Room room1 = booking.getRoom();
        User user1 = booking.getUser();
        List<AdditionalOption> options = booking.getOptions();
        room1.setReserved(true);

        roomRepository.save(room1);
        userRepository.save(user1);
        bookingRepository.save(booking);
        return booking;
    }

    @GetMapping("{id}")
    public List<AdditionalOption> getOptionsInBooking(@PathVariable int id) {
        Iterable<Booking> bookings = bookingRepository.findAll();
        Booking booking = null;

        for (Booking b : bookings) {
            if (b.getId() == id) {
                booking = b;
            }
        }
        if (booking == null) {
            return Collections.emptyList();
        }
        List<AdditionalOption> options = booking.getOptions();
        return options;
    }
}

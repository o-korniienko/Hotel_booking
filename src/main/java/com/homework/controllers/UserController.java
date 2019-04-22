package com.homework.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.homework.domain.Booking;
import com.homework.domain.User;
import com.homework.repositories.BookingRepository;
import com.homework.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @PostMapping
    public User createUser(@RequestBody String userJson) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        User user = gson.fromJson(userJson, User.class);
        userRepository.save(user);
        return user;
    }

    @GetMapping
    public Iterable<User>  getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("{user_id}")
    public Booking getBooking(@PathVariable long user_id) {
        Iterable<Booking> bookings = bookingRepository.findAll();
        Booking booking = null;
        for (Booking b : bookings) {
            if (b.getUser().getId() == user_id) {
                booking = b;
            }
        }
        return booking;
    }

    @GetMapping(path = "/total_price")
    public long totalPrice(@RequestParam(value = "id") long user_id) {
        Booking booking = getBooking(user_id);
        if (booking == null) {
            return 0;
        }
        return booking.getTotal_price();
    }
}

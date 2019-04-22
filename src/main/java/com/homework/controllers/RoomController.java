package com.homework.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.homework.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.homework.repositories.RoomRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @PostMapping
    public Room addRoom(@RequestBody String roomJson) {
        Room room = gson.fromJson(roomJson, Room.class);
        roomRepository.save(room);
        return room;
    }

    @GetMapping
    public List<Room> getAllRooms() {
        Iterable<Room> rooms = roomRepository.findAll();
        List<Room> listRooms = new ArrayList<>();
        for (Room room : rooms) {
            listRooms.add(room);
        }
        return listRooms;
    }

    @GetMapping(path = "/allAvailable")
    public List<Room> getAllAvailable() {
        Iterable<Room> allRooms;
        List<Room> availableRooms = new ArrayList<>();
        allRooms = roomRepository.findAll();
        for (Room room : allRooms) {
            if (!room.isReserved()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    @GetMapping(path = "/filter_by")
    public List<Room> filterBy(@RequestParam(value = "category") Room.Category category) {
        Iterable<Room> rooms = roomRepository.findAll();
        List<Room> filtered = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getCategory() == category) {
                filtered.add(room);
            }
        }
        return filtered;
    }
}

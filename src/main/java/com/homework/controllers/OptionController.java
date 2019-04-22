package com.homework.controllers;

import com.homework.domain.AdditionalOption;
import com.homework.repositories.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/options")
public class OptionController {

    @Autowired
    private OptionRepository optionRepository;

    @GetMapping
    public Iterable<AdditionalOption> getOptions(){
        return optionRepository.findAll();
    }


}

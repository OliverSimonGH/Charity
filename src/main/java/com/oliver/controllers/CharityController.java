package com.oliver.controllers;

import com.oliver.entities.Charity;
import com.oliver.repositories.CharityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CharityController {

    private CharityRepository charityRepository;

    @Autowired
    public CharityController(CharityRepository charityRepository) {
        this.charityRepository = charityRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String requestCharities(Model model){
        model.addAttribute("title", "Charities");
        model.addAttribute("charities", charityRepository.findAll());
        return "charity/charities";
    }

    @RequestMapping(value = "/charity/{id}", method = RequestMethod.GET)
    public String requestCharityById(@PathVariable(value = "id") int id, Model model){
        Charity charity = charityRepository.findCharityByCharityID(id);
        model.addAttribute("title", charity.getCharityName() + "Charity");
        model.addAttribute("charity", charity);
        return "charity/charity";
    }

}

package com.oliver.controllers;

import com.oliver.entities.Charity;
import com.oliver.repositories.CharityRepository;
import com.oliver.services.CharityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CharityController {

    private CharityService charityService;

    @Autowired
    public CharityController(CharityService charityService) {
        this.charityService = charityService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String requestCharities(Model model){
        model.addAttribute("title", "Charities");
        model.addAttribute("charities", charityService.findAll());
        return "charity/charities";
    }

    @RequestMapping(value = "/charity/{id}", method = RequestMethod.GET)
    public String requestCharityById(@PathVariable(value = "id") int id, Model model){
        Charity charity = charityService.findCharityByCharityID(id);
        model.addAttribute("title", charity.getCharityName() + " Charity");
        model.addAttribute("charity", charity);
        return "charity/charity";
    }

    @RequestMapping(value = "/charity/{id}/donate", method = RequestMethod.GET)
    public String requestCharityDonateById(@PathVariable(value = "id") int id, Model model){
        Charity charity = charityService.findCharityByCharityID(id);
        model.addAttribute("title", "Donate to " + charity.getCharityName());
        model.addAttribute("charity", charity);
        return "charity/charitydonate";
    }

}

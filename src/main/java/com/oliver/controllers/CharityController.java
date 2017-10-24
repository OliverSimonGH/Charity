package com.oliver.controllers;

import com.oliver.entities.Charity;
import com.oliver.repositories.CharityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c1633899 on 14/10/2017.
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CharityController {

    private CharityRepository charityRepository;

    @Autowired
    public CharityController(CharityRepository charityRepository) {
        this.charityRepository = charityRepository;
    }

    @RequestMapping(value = "/charities", method = RequestMethod.GET)
    public List<Charity> requestCharities(){
        return charityRepository.findAll();
    }

    @RequestMapping(value = "/charity/{id}", method = RequestMethod.GET)
    public Charity requestCharityById(@PathVariable(value = "id") int id){
        return charityRepository.findCharityByCharityID(id);
    }

    @RequestMapping(value = "/charity", method = RequestMethod.GET)
    public ArrayList<Charity> requestCharitiesByName(@RequestParam(value = "name") ArrayList<String> name) {
        ArrayList<Charity> charity1 = new ArrayList<>();

        if (name.size() != 0) {
            name.forEach(s -> charity1.add(charityRepository.findCharityByCharityName(s)));
            return charity1;
        }

        return null;
    }


}

package com.oliver.controllers;

import com.oliver.entities.Charity;
import com.oliver.entities.Donation;
import com.oliver.entities.Sponsor;
import com.oliver.repositories.CharityRepository;
import com.oliver.services.ActivityService;
import com.oliver.services.CharityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CharityController {

    private CharityService charityService;
    private ActivityService activityService;

    @Autowired
    public CharityController(CharityService charityService, ActivityService activityService) {
        this.charityService = charityService;
        this.activityService = activityService;
    }

    @RequestMapping(value = "/charities", method = RequestMethod.GET)
    public List<Charity> requestCharities(){
        return charityService.findAll();
    }

    @RequestMapping(value = "/charity/{id}", method = RequestMethod.GET)
    public Charity requestCharityById(@PathVariable(value = "id") int id){
        return charityService.findCharityByCharityID(id);
    }

    @RequestMapping(value = "/charity", method = RequestMethod.GET)
    public List<Charity> requestCharitiesByName(@RequestParam(value = "name") ArrayList<String> name){
        return charityService.findCharitiesByName(name);
    }

    @RequestMapping(value = "/charity/{id}/donations", method = RequestMethod.GET)
    public List<Donation> requestCharityDonationsById(@PathVariable(value = "id") int id, @RequestParam(value = "page", required = false) final Integer page,  @RequestParam(value = "limit", required = false) final Integer limit, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "sortBy", required = false) String sortBy){
            return activityService.findAllDonationsByCharityIdLimit(id, page, limit, sort, sortBy);
    }

    @RequestMapping(value = "/charity/{id}/sponsors", method = RequestMethod.GET)
    public List<Sponsor> requestCharitySponsorsById(@PathVariable(value = "id") int id){
        return activityService.findAllSponsorsByCharityId(id);
    }

}

package com.oliver.controllers;

import com.oliver.data.ActivityReport;
import com.oliver.data.DonationReport;
import com.oliver.entities.Charity;
import com.oliver.entities.Donation;
import com.oliver.entities.Sponsor;
import com.oliver.config.NoSuchResourceException;
import com.oliver.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CharityController {

    private CharityService charityService;
    private DonationService donationService;
    private SponsorService sponsorService;
    private ActivityService activityService;

    @Autowired
    public CharityController(CharityService charityService, DonationService donationService, SponsorService sponsorService, ActivityService activityService) {
        this.charityService = charityService;
        this.donationService = donationService;
        this.sponsorService = sponsorService;
        this.activityService = activityService;
    }

    @RequestMapping(value = "/charities", method = RequestMethod.GET)
    public List<Charity> requestCharities(){
        return charityService.findAll();
    }

    @RequestMapping(value = "/charity/{id}", method = RequestMethod.GET)
    public Charity requestCharityById(@PathVariable(value = "id") Long id) throws NoSuchResourceException{
        Charity charity = charityService.findCharityByCharityID(id);
        if (charity != null) return charity;
        else throw new NoSuchResourceException();
    }

    @RequestMapping(value = "/charity", method = RequestMethod.GET)
    public List<Charity> requestCharitiesByName(@RequestParam(value = "name") ArrayList<String> name){
        return charityService.findCharitiesByName(name);
    }

    @RequestMapping(value = "/charity/{id}/donations", method = RequestMethod.GET)
    public List<Donation> requestCharityDonationsById(@PathVariable(value = "id") Long id){
            return donationService.findAllByCharityId(id);
    }

    @RequestMapping(value = "/charity/{id}/donations/total", method = RequestMethod.GET)
    public DonationReport requestCharityDonationsTotalById(@PathVariable(value = "id") Long id) {
        return donationService.findAllDonationsTotalByCharityId(id);
    }

    @RequestMapping(value = "/charity/{id}/sponsors", method = RequestMethod.GET)
    public List<Sponsor> requestCharitySponsorsById(@PathVariable(value = "id") Long id){
        return sponsorService.findAllByCharityId(id);
    }

    @RequestMapping(value = "/charity/{id}/events", method = RequestMethod.GET)
    public List<ActivityReport> requestCharityEventsById(@PathVariable(value = "id") Long id){
         return activityService.getAllActivities(id);
    }

}

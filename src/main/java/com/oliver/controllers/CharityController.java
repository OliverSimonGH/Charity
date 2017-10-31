package com.oliver.controllers;

import com.oliver.data.ActivityReport;
import com.oliver.data.DonationReport;
import com.oliver.entities.ActivityInterface;
import com.oliver.entities.Charity;
import com.oliver.entities.Donation;
import com.oliver.entities.Sponsor;
import com.oliver.services.ActivityServiceImpl;
import com.oliver.services.CharityServiceImpl;
import com.oliver.services.DonationServiceImpl;
import com.oliver.services.SponsorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CharityController {

    private CharityServiceImpl charityServiceImpl;
    private DonationServiceImpl donationServiceImpl;
    private SponsorServiceImpl sponsorServiceImpl;
    private ActivityServiceImpl activityServiceImpl;

    @Autowired
    public CharityController(CharityServiceImpl charityServiceImpl, DonationServiceImpl donationServiceImpl, SponsorServiceImpl sponsorServiceImpl, ActivityServiceImpl activityServiceImpl) {
        this.charityServiceImpl = charityServiceImpl;
        this.donationServiceImpl = donationServiceImpl;
        this.sponsorServiceImpl = sponsorServiceImpl;
        this.activityServiceImpl = activityServiceImpl;
    }

    @RequestMapping(value = "/charities", method = RequestMethod.GET)
    public List<Charity> requestCharities(){
        return charityServiceImpl.findAll();
    }

    @RequestMapping(value = "/charity/{id}", method = RequestMethod.GET)
    public Charity requestCharityById(@PathVariable(value = "id") int id){
        return charityServiceImpl.findCharityByCharityID(id);
    }

    @RequestMapping(value = "/charity", method = RequestMethod.GET)
    public List<Charity> requestCharitiesByName(@RequestParam(value = "name") ArrayList<String> name){
        return charityServiceImpl.findCharitiesByName(name);
    }

    @RequestMapping(value = "/charity/{id}/donations", method = RequestMethod.GET)
    public List<Donation> requestCharityDonationsById(@PathVariable(value = "id") int id){
            return donationServiceImpl.findAllByCharityId(id);
    }

    @RequestMapping(value = "/charity/{id}/donations/total", method = RequestMethod.GET)
    public DonationReport requestCharityDonationsTotalById(@PathVariable(value = "id") int id) {
        return donationServiceImpl.findAllDonationsTotalByCharityId(id);
    }

    @RequestMapping(value = "/charity/{id}/sponsors", method = RequestMethod.GET)
    public List<Sponsor> requestCharitySponsorsById(@PathVariable(value = "id") int id){
        return sponsorServiceImpl.findAllByCharityId(id);
    }

    @RequestMapping(value = "/charity/{id}/events", method = RequestMethod.GET)
    public List<ActivityReport> requestCharityEventsById(@PathVariable(value = "id") int id){
        return activityServiceImpl.getAllActivities(id);
    }

}

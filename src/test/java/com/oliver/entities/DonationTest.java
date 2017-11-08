package com.oliver.entities;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by c1633899 on 03/11/2017.
 */
public class DonationTest {

    Donation donation;

    @Before
    public void setup(){
        Address address = new Address("Queen", 7, "Column Road", "Terrace", "Kelasuri", "38J YTH", "GB");
        Donor donor =  new Donor("Oliver", "Simon", address);
        donation = new Donation((long) 1, 30958, new Date(), true, true, true, donor, null, null);
    }

    @Test
    public void checkIfEligibleForGiftAidAllTrue(){
        assertEquals(true, donation.isEligibleGiftAid());
    }

    @Test
    public void checkIfEligibleForGiftAidOneOptionFalse(){
        donation.setOwnMoney(false);
        assertEquals(false, donation.isEligibleGiftAid());
    }

    @Test
    public void CheckIfResidentIsFromGBTrue(){
        assertEquals(true, donation.isGBResident());
    }

    @Test
    public void checkIfResidentIsFromGBFalse(){
        donation.getDonor().getAddress().setCountryISOCode("NA");
        assertEquals(false, donation.isGBResident());
    }

    @Test
    public void checkIfPostcodeIsValidTrue(){
        assertEquals(true, donation.checkPostcode());
    }

    @Test
    public void checkIfPostcodeIsValidFalse(){
        donation.getDonor().getAddress().setPostcode("QW");
        assertEquals(false, donation.checkPostcode());
    }
}
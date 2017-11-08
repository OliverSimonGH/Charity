package com.oliver.controllers;

import com.oliver.data.DonationReport;
import com.oliver.entities.*;
import com.oliver.services.ActivityService;
import com.oliver.services.CharityService;
import com.oliver.services.DonationService;
import com.oliver.services.SponsorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by c1633899 on 07/11/2017.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CharityController.class)
public class CharityControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CharityService charityService;

    @MockBean
    private DonationService donationService;

    @MockBean
    private SponsorService sponsorService;

    @MockBean
    private ActivityService activityService;

    private Donor donor;

    private List<Charity> charities;

    private List<Donation> donations;

    private List<Sponsor> sponsors;

    @Before
    public void setup(){
//        https://stackoverflow.com/questions/9112770/how-to-convert-calendar-to-java-sql-date-in-java
        Calendar calendar = Calendar.getInstance();
        Date firstDate = new Date(calendar.getTime().getTime());
        calendar.set(Calendar.MONTH, 1);
        Date secondDate = new Date(calendar.getTime().getTime());
//        ---------------------------------------------------------------

        donor = new Donor("Oliver", "Simon", new Address(null, null, null, null, null, "TYH 5UH", "GB"));

        sponsors = new ArrayList<>(Arrays.asList(
                new Sponsor(1L, "Thomas Hanks", null, secondDate, null, null, null, null, null),
                new Sponsor(1L, "Sinclair", null, firstDate, null, null, null, null, null)
        ));

        donations = new ArrayList<>(Arrays.asList(
                new Donation(1L, 10000, firstDate, false, false, false, donor, null, null),
                new Donation(2L, 20000, secondDate, true, true, true, donor, null, null)
        ));

        charities = new ArrayList<>(Arrays.asList(
                new Charity(1L, "NSPCC", null, null, donations, null),
                new Charity(2L, "OXFAM", null, null, donations, null)
        ));
    }

    @Test
    public void expectToSeeAllCharityNamesInResponse() throws Exception {
        when(charityService.findAll()).thenReturn(charities);
        this.mvc.perform(get("/api/charities")).andExpect(status().isOk())
                .andExpect(content().string(containsString("NSPCC")))
                .andExpect(content().string(containsString("OXFAM")));
    }

    @Test
    public void expectToSeeCertainCharityByIdInResponse() throws Exception {
        when(charityService.findCharityByCharityID(1L)).thenReturn(charities.get(0));
        this.mvc.perform(get("/api/charity/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"charityID\":1,\"charityName\":\"NSPCC\",\"charityNumber\":null,\"purpose\":null}")));
    }

    @Test
    public void expectToSeeNoSuchResourceExceptionInResponse() throws Exception {
        when(charityService.findAll()).thenReturn(charities);
        this.mvc.perform(get("/api/charity/12345"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("No resource available on this URL")));
    }

    @Test
    public void expectToSeeASingleCharityDonationsMade() throws Exception{
        when(donationService.findAllByCharityId(1L)).thenReturn(donations);

        this.mvc.perform(get("/api/charity/1/donations"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[{\"id\":1,\"amountInPence\":10000,\"donationDate\":\"2017-11-08\",\"ownMoney\":false,\"hasNoBenefitToDonor\":false,\"wishesToGiftAid\":false,\"eligibleGiftAid\":false,\"gbresident\":true,\"pounds\":100},{\"id\":2,\"amountInPence\":20000,\"donationDate\":\"2017-02-08\",\"ownMoney\":true,\"hasNoBenefitToDonor\":true,\"wishesToGiftAid\":true,\"eligibleGiftAid\":true,\"gbresident\":true,\"pounds\":200}]")));

    }

    @Test
    public void expectToSeeASingleCharitySponsorsCreated() throws Exception{
        when(sponsorService.findAllByCharityId(1L)).thenReturn(sponsors);

        this.mvc.perform(get("/api/charity/1/sponsors"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[{\"fundraiserName\":\"Thomas Hanks\",\"fundraisingAction\":null,\"dateCreated\":\"2017-02-08\",\"firstValidDay\":null,\"lastValidDay\":null,\"furl\":null,\"id\":1},{\"fundraiserName\":\"Sinclair\",\"fundraisingAction\":null,\"dateCreated\":\"2017-11-08\",\"firstValidDay\":null,\"lastValidDay\":null,\"furl\":null,\"id\":1}]")));
    }

    @Test
    public void expectToSeeDonationTotalForACertainCharity() throws Exception {
        DonationReport report = new DonationReport(donations);
        given(donationService.findAllDonationsTotalByCharityId(1L)).willReturn(report);

        this.mvc.perform(get("/api/charity/1/donations/total"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"total\":340,\"amount\":300,\"giftaid\":40}")));
    }
}
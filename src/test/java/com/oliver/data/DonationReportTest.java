package com.oliver.data;

import com.oliver.entities.Address;
import com.oliver.entities.Donation;
import com.oliver.entities.Donor;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by c1633899 on 08/11/2017.
 */
public class DonationReportTest {

    private List<Donation> donations;

    @Before
    public void setup(){
        Address addressOne = new Address("Queen", 7, "Column Road", "Terrace", "Kelasuri", "38J YTH", "GB");
        Address addressTwo = new Address("King", 65, "Maple Street", "Pine", "Green Wood", "3", "NA");

        Donor donorOne =  new Donor("Oliver", "Simon", addressOne);
        Donor donorTwo = new Donor("Thomas", "Rhys", addressTwo);

        donations = new ArrayList<>(Arrays.asList(
                new Donation(1L, 10000, null, false, false, false, donorOne, null, null),
                new Donation(2L, 20000, null, true, true, true, donorOne, null, null),
                new Donation(3L, 40000, null, true, true, true, donorTwo, null, null)
        ));
    }

    @Test
    public void calculateDonationReportWithDonations(){
        DonationReport donationReport = new DonationReport(donations);

        assertEquals(donationReport.getAmount(), 700);
        assertEquals(donationReport.getGiftaid(), 40);
        assertEquals(donationReport.getTotal(), 740);
    }

    @Test
    public void calculateDonationReportWithNoDonations() {
        List<Donation> donations1 = new ArrayList<>();
        DonationReport donationReport = new DonationReport(donations1);

        assertEquals(donationReport.getAmount(), 0);
        assertEquals(donationReport.getGiftaid(), 0);
        assertEquals(donationReport.getTotal(), 0);
    }
}
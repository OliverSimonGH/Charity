package com.oliver.services;

import com.oliver.data.DonationReport;
import com.oliver.entities.Donation;

import java.util.List;

/**
 * Created by c1633899 on 30/10/2017.
 */
public interface DonationService {

    List<Donation> findAllByCharityId(int num);
    DonationReport findAllDonationsTotalByCharityId(int num);
}

package com.oliver.services;

import com.oliver.entities.Charity;
import com.oliver.entities.Donation;

import java.util.List;

/**
 * Created by c1633899 on 30/10/2017.
 */
public interface DonationService {

    List<Donation> findAllByCharityID(int num);
    List<Donation> findAllDonationsByCharityId(int num, Integer page, Integer size, String sort, String sortBy);
    int findAllDonationsTotalByCharityId(int num, String type);
}

package com.oliver.services;

import com.oliver.entities.Charity;
import com.oliver.entities.Donation;
import com.oliver.entities.Sponsor;

import java.util.List;
import java.util.Optional;

/**
 * Created by c1633899 on 27/10/2017.
 */
public interface ActivityServiceInterface {

    List<Donation> findAllDonationsByCharityId(int num);
    List<Donation> findAllDonationsByCharityIdLimit(int num, Integer page, Integer size, String sort, String sortBy);
    List<Sponsor> findAllSponsorsByCharityId(int num);
}

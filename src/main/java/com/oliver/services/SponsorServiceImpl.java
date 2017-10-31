package com.oliver.services;

import com.oliver.entities.Charity;
import com.oliver.entities.Sponsor;
import com.oliver.repositories.CharityRepository;
import com.oliver.repositories.DonationRepository;
import com.oliver.repositories.SponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by c1633899 on 30/10/2017.
 */

@Service
public class SponsorServiceImpl implements SponsorService {

    @Qualifier("JPA")
    private SponsorRepository sponsorRepository;

    @Autowired
    public SponsorServiceImpl(SponsorRepository sponsorRepository) {
        this.sponsorRepository = sponsorRepository;
    }

    @Override
    public List<Sponsor> findAllByCharityId(int num) {
        return sponsorRepository.findAllByCharity(num);
    }
}

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

    @Qualifier("JPA")
    private CharityRepository charityRepository;

    @Autowired
    public SponsorServiceImpl(SponsorRepository sponsorRepository, CharityRepository charityRepository) {
        this.sponsorRepository = sponsorRepository;
        this.charityRepository = charityRepository;
    }

    @Override
    public List<Sponsor> findAllByCharityId(int num) {
        Charity charity = charityRepository.findCharityByCharityID(num);
        return sponsorRepository.findAllByCharity(charity);
    }
}

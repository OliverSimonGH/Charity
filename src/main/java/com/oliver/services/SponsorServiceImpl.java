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

    private CharityService charityService;

    @Autowired
    public SponsorServiceImpl(SponsorRepository sponsorRepository, CharityService charityService) {
        this.sponsorRepository = sponsorRepository;
        this.charityService = charityService;
    }

    @Override
    public List<Sponsor> findAllByCharityId(Long num) {
        Charity charity = charityService.findCharityByCharityID(num);
        return sponsorRepository.findAllByCharity(charity);
    }
}

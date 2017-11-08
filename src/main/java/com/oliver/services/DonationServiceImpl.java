package com.oliver.services;

import com.oliver.data.DonationReport;
import com.oliver.entities.Charity;
import com.oliver.entities.Donation;
import com.oliver.repositories.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by c1633899 on 30/10/2017.
 */
@Service
public class DonationServiceImpl implements DonationService {

    @Qualifier("JPA")
    private DonationRepository donationRepository;

    private CharityService charityService;

    @Autowired
    public DonationServiceImpl(DonationRepository donationRepository, CharityService charityService) {
        this.donationRepository = donationRepository;
        this.charityService = charityService;
    }

    @Override
    public List<Donation> findAllByCharityId(Long num){
        Charity charity = charityService.findCharityByCharityID(num);
        return donationRepository.findAllByCharity(charity);
    }

    @Override
    public DonationReport findAllDonationsTotalByCharityId(Long num) {
        Charity charity = charityService.findCharityByCharityID(num);
        return new DonationReport(donationRepository.findAllByCharity(charity));
    }
}

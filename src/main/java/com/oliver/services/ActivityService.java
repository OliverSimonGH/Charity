package com.oliver.services;

import com.oliver.entities.Charity;
import com.oliver.entities.Donation;
import com.oliver.entities.Sponsor;
import com.oliver.repositories.CharityRepository;
import com.oliver.repositories.DonationRepository;
import com.oliver.repositories.SponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService implements ActivityServiceInterface{

    @Qualifier("JPA")
    private DonationRepository donationRepository;

    @Qualifier("JPA")
    private SponsorRepository sponsorRepository;

    @Qualifier("JPA")
    private CharityRepository charityRepository;

    @Autowired
    public ActivityService(DonationRepository donationRepository, SponsorRepository sponsorRepository, CharityRepository charityRepository) {
        this.donationRepository = donationRepository;
        this.sponsorRepository = sponsorRepository;
        this.charityRepository = charityRepository;
    }

    @Override
    public List<Donation> findAllDonationsByCharityIdLimit(int num, Integer page, Integer size, String sort, String sortBy) {
        PageRequest pageRequest = new PageRequest((page == null ? 0 : page), (size == null ? Integer.MAX_VALUE : size), (sort == null ? Sort.Direction.DESC : getSort(sort)), (sortBy == null ? "amountInPence" : sortBy));
        Charity charity = charityRepository.findCharityByCharityID(num);
        return donationRepository.findAllByCharity(charity, pageRequest);
    }

    @Override
    public List<Donation> findAllDonationsByCharityId(int num) {
        Charity charity = charityRepository.findCharityByCharityID(num);
        return donationRepository.findAllByCharity(charity);
    }

    @Override
    public List<Sponsor> findAllSponsorsByCharityId(int num) {
        Charity charity = charityRepository.findCharityByCharityID(num);
        return sponsorRepository.findAllByCharity(charity);
    }

    private Sort.Direction getSort(String val){
        if (val.equalsIgnoreCase("asc")){
            return Sort.Direction.ASC;
        } else  if (val.equalsIgnoreCase("desc")){
            return Sort.Direction.DESC;
        } else {
            return Sort.Direction.DESC;
        }
    }
}

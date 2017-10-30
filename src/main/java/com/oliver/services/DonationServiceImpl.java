package com.oliver.services;

import com.oliver.entities.ActivityInterface;
import com.oliver.entities.Charity;
import com.oliver.entities.Donation;
import com.oliver.repositories.CharityRepository;
import com.oliver.repositories.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by c1633899 on 30/10/2017.
 */
@Service
public class DonationServiceImpl implements  DonationService {

    @Qualifier("JPA")
    private DonationRepository donationRepository;

    @Qualifier("JPA")
    private CharityRepository charityRepository;

    @Autowired
    public DonationServiceImpl(DonationRepository donationRepository, CharityRepository charityRepository) {
        this.donationRepository = donationRepository;
        this.charityRepository = charityRepository;
    }

    @Override
    public List<Donation> findAllByCharityID(int num) {
        Charity charity = charityRepository.findCharityByCharityID(num);
        return donationRepository.findAllByCharity(charity);
    }

    @Override
    public List<Donation> findAllDonationsByCharityId(int num, Integer page, Integer size, String sort, String sortBy) {
        PageRequest pageRequest = new PageRequest((page == null ? 0 : page), (size == null ? Integer.MAX_VALUE : size), (sort == null ? Sort.Direction.DESC : getSort(sort)), (sortBy == null ? "amountInPence" : sortBy));
        Charity charity = charityRepository.findCharityByCharityID(num);
        return donationRepository.findAllByCharity(charity, pageRequest);
    }

    @Override
    public int findAllDonationsTotalByCharityId(int num, String type) {
        int result;
        Charity charity = charityRepository.findCharityByCharityID(num);

        if (type == null || !type.equalsIgnoreCase("giftaid")){
            result = donationRepository.findAllByCharity(num);
        } else {
            result = (int) donationRepository.findAllByCharity(charity)
                    .stream()
                    .filter(Donation::isEligibleGiftAid)
                    .mapToDouble(donation -> donation.getAmountInPence() * 0.05)
                    .sum();
        }
        return result;
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

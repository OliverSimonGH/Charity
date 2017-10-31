package com.oliver.services;

import com.oliver.data.DonationReport;
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
public class DonationServiceImpl implements DonationService {

    @Qualifier("JPA")
    private DonationRepository donationRepository;


    @Autowired
    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }


    @Override
    public List<Donation> findAllByCharityId(int num) {
        return donationRepository.findAllByCharityId(num);
    }

    @Override
    public DonationReport findAllDonationsTotalByCharityId(int num) {
        int donationReport = donationRepository.findAllByCharitySum(num);
        return new DonationReport(donationReport + (int)(donationReport * 0.2), donationReport, (int)(donationReport * 0.2));
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

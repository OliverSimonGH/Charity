package com.oliver.services;

import com.oliver.entities.ActivityInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Qualifier("JPA")
    private DonationServiceImpl donationServiceImpl;

    @Qualifier("JPA")
    private SponsorServiceImpl sponsorServiceImpl;

    @Autowired
    public ActivityServiceImpl(DonationServiceImpl donationServiceImpl, SponsorServiceImpl sponsorServiceImpl) {
        this.donationServiceImpl = donationServiceImpl;
        this.sponsorServiceImpl = sponsorServiceImpl;
    }

    @Override
    public List<ActivityInterface> getAllActivities(int id) {
        ArrayList<ActivityInterface> result = new ArrayList<>();
        result.addAll(donationServiceImpl.findAllByCharityID(id));
        result.addAll(sponsorServiceImpl.findAllByCharityId(id));

        return result.stream()
                .sorted(Comparator.comparing(ActivityInterface::getDate, Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toList());
    }
}

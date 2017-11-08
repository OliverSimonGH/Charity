package com.oliver.services;

import com.oliver.data.ActivityReport;
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
    private DonationService donationService;

    @Qualifier("JPA")
    private SponsorService sponsorService;

    @Autowired
    public ActivityServiceImpl(DonationService donationService, SponsorService sponsorService) {
        this.donationService = donationService;
        this.sponsorService = sponsorService;
    }

    @Override
    public List<ActivityReport> getAllActivities(Long id) {
        ArrayList<ActivityInterface> result = new ArrayList<>();
        result.addAll(donationService.findAllByCharityId(id));
        result.addAll(sponsorService.findAllByCharityId(id));

        return result.stream()
                .map(activityInterface -> new ActivityReport(activityInterface.getPerson(), activityInterface.getEvent(), activityInterface.getDate()))
                .sorted(Comparator.comparing(ActivityReport::getDate, Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toList());
    }
}

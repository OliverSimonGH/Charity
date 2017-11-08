package com.oliver.services;

import com.oliver.entities.Charity;
import com.oliver.entities.Sponsor;

import java.util.List;

/**
 * Created by c1633899 on 30/10/2017.
 */
public interface SponsorService {

    List<Sponsor> findAllByCharityId(Long num);
}

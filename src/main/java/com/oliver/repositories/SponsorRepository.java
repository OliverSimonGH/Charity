package com.oliver.repositories;

import com.oliver.entities.Charity;
import com.oliver.entities.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by c1633899 on 27/10/2017.
 */
public interface SponsorRepository extends JpaRepository<Sponsor, Long> {

    List<Sponsor> findAllByCharity(Charity charity);
}

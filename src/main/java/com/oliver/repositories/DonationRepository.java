package com.oliver.repositories;

import com.oliver.entities.Charity;
import com.oliver.entities.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by c1633899 on 27/10/2017.
 */
public interface DonationRepository extends JpaRepository<Donation, Long> {

    List<Donation> findAllByCharity(Charity charity);
}

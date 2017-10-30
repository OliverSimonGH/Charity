package com.oliver.repositories;

import com.oliver.entities.Charity;
import com.oliver.entities.Donation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by c1633899 on 27/10/2017.
 */
public interface DonationRepository extends JpaRepository<Donation, Long> {

    List<Donation> findAllByCharity(Charity charity, Pageable pageable);
    List<Donation> findAllByCharity(Charity charity);

    @Query(value = "SELECT SUM(amount_in_pence) FROM donation WHERE charity_id = :charity_id", nativeQuery = true)
    int findAllByCharity(@Param("charity_id") int charity_id);
}

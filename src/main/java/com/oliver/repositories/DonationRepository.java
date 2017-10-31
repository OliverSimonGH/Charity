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

    @Query(value = "SELECT * FROM donation WHERE charity_id = :charity_id ORDER BY amount_in_pence DESC", nativeQuery = true )
    List<Donation> findAllByCharityId(@Param("charity_id") int charity_id);

    @Query(value = "SELECT SUM(amount_in_pence) / 100 FROM donation WHERE charity_id = :charity_id", nativeQuery = true)
    int findAllByCharitySum(@Param("charity_id") int charity_id);
}

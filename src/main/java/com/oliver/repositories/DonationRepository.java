package com.oliver.repositories;

import com.oliver.entities.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by c1633899 on 27/10/2017.
 */
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query(value = "SELECT * FROM donation WHERE charity_id = :charity_id ORDER BY amount_in_pence DESC", nativeQuery = true )
    List<Donation> findAllByCharityId(@Param("charity_id") int charity_id);

}

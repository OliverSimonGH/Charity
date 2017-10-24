package com.oliver.repositories;

import com.oliver.entities.Charity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by c1633899 on 20/10/2017.
 */
public interface CharityRepository extends JpaRepository<Charity, Long>{

    List<Charity> findAll();
    Charity findCharityByCharityID(int id);
    Charity findCharityByCharityName(String charityName);
}

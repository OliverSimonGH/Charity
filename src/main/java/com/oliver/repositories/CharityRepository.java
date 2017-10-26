package com.oliver.repositories;

import com.oliver.entities.Charity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharityRepository extends JpaRepository<Charity, Long>{

    List<Charity> findAll();
    Charity findCharityByCharityID(int id);
    Charity findCharityByCharityName(String charityName);
}

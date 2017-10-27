package com.oliver.services;

import com.oliver.entities.Charity;
import com.oliver.repositories.CharityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by c1633899 on 27/10/2017.
 */
@Service
public class CharityService implements CharityServiceInterface {

    @Qualifier("JPA")
    private CharityRepository charityRepository;

    @Autowired
    public CharityService(CharityRepository charityRepository) {
        this.charityRepository = charityRepository;
    }

    @Override
    public List<Charity> findAll() {
        return charityRepository.findAll();
    }

    @Override
    public Charity findCharityByCharityID(int id) {
        return charityRepository.findCharityByCharityID(id);
    }
}

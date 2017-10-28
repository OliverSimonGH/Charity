package com.oliver.services;

import com.oliver.entities.Charity;
import com.oliver.repositories.CharityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Override
    public List<Charity> findCharitiesByName(ArrayList<String> charity){
        List<Charity> result = charity.stream()
                .map(s -> charityRepository.findCharityByCharityName(s))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (result.size() != 0) return result;
        else return null;
    }
}

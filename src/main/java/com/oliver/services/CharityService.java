package com.oliver.services;

import com.oliver.entities.Charity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c1633899 on 27/10/2017.
 */
public interface CharityService {

    List<Charity> findAll();
    Charity findCharityByCharityID(int id);
    List<Charity> findCharitiesByName(ArrayList<String> charity);
}

package com.oliver.services;

import com.oliver.entities.ActivityInterface;

import java.util.List;

/**
 * Created by c1633899 on 27/10/2017.
 */
public interface ActivityService {

    List<ActivityInterface> getAllActivities(int id);
}

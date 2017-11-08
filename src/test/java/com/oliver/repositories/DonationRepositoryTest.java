package com.oliver.repositories;

import com.oliver.entities.Charity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by c1633899 on 07/11/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DonationRepositoryTest {

    @Autowired
    DonationRepository donationRepository;

    @Autowired
    CharityRepository charityRepository;

    @Test
    public void RepositoryHasData() throws Exception {
        assertThat(donationRepository.findAll()).isNotEmpty();
    }

    @Test
    public void findAllDonationsByCharity(){
        Charity charity = charityRepository.findCharityByCharityID((long) 1);
        assertThat(donationRepository.findAllByCharity(charity)).isNotEmpty();
    }

    @Test
    public void findAllDonationsByCharityNull(){
        Charity charity = charityRepository.findCharityByCharityID((long) Integer.MAX_VALUE);
        assertThat(charity).isNull();
        assertThat(donationRepository.findAllByCharity(charity)).isEmpty();
    }

}
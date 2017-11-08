package com.oliver.repositories;

import com.oliver.entities.Charity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by c1633899 on 07/11/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CharityRepositoryTest {

    @Autowired
    private CharityRepository repository;

    @Test
    public void RepositoryHasData(){
        List<Charity> charities = repository.findAll();
        assertThat(charities).isNotEmpty();
    }

    @Test
    public void findExistingCharityByID(){
        Charity charity = repository.findCharityByCharityID((long) 1);
        assertThat(charity.getCharityID()).isEqualTo(1);
    }

    @Test
    public void findNonExistingCharityByID(){
        Charity charity = repository.findCharityByCharityID((long) Integer.MAX_VALUE);
        assertThat(charity).isNull();
    }

    @Test
    public void findExistingCharityByName(){
        Charity charity = repository.findCharityByCharityName("NSPCC");
        assertThat(charity).isNotNull();
    }

    @Test
    public void findNonExistingCharityByName(){
        Charity charity = repository.findCharityByCharityName("NSC");
        assertThat(charity).isNull();
    }

}
package com.oliver.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by c1633899 on 10/3/2017.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "charity")
public class Charity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int charityID;

    @Column(name = "name")
    private String charityName;

    @Column(name = "registration_id")
    private String charityNumber;



}


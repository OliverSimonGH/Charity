package com.oliver.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.org.apache.xerces.internal.xs.ShortList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private Long charityID;

    @Column(name = "name")
    private String charityName;

    @Column(name = "registration_id")
    private String charityNumber;

    @Column(name = "purpose")
    private String purpose;

    @JsonIgnore
    @OneToMany(mappedBy = "charity")
    private List<Donation> donations;

    @JsonIgnore
    @OneToMany(mappedBy = "charity")
    private List<Sponsor> sponsors;

}


package com.oliver.entities;

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

    public Charity(String charityName, String charityNumber, String purpose) {
        this.charityName = charityName;
        this.charityNumber = charityNumber;
        this.purpose = purpose;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int charityID;

    @Column(name = "name")
    private String charityName;

    @Column(name = "registration_id")
    private String charityNumber;

    @Column(name = "purpose")
    private String purpose;

    @OneToMany(mappedBy = "charity", cascade = CascadeType.ALL)
    private List<Donation> donations;

    @OneToMany(mappedBy = "charity", cascade = CascadeType.ALL)
    private List<Sponsor> sponsors;

}


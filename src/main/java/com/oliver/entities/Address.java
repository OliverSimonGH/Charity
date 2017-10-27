package com.oliver.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    public Address(String buildingName, int buildingNumber, String street, String district, String city, String postcode, String countryISOCode) {
        this.buildingName = buildingName;
        this.buildingNumber = buildingNumber;
        this.street = street;
        this.district = district;
        this.city = city;
        this.postcode = postcode;
        this.countryISOCode = countryISOCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int Id;

    @Column(name = "building_name")
    private String buildingName;

    @Column(name = "building_number")
    private int buildingNumber;

    @Column(name = "street")
    private String street;

    @Column(name = "district")
    private String district;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postcode;

    @Column(name = "country_iso_code")
    private String countryISOCode;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<Donor> donors;


}

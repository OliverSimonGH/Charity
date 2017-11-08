package com.oliver.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "donation")
public class Donation implements ActivityInterface{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount_in_pence")
    private Integer amountInPence;

    @Column(name = "donation_date")
    @Temporal(TemporalType.DATE)
    private Date donationDate;

    @Column(name = "is_own_money")
    private Boolean ownMoney;

    @Column(name = "has_no_benefit_to_donor")
    private Boolean hasNoBenefitToDonor;

    @Column(name = "wishes_to_gift_aid")
    private Boolean wishesToGiftAid;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "donor_id")
    private Donor donor;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "charity_id")
    private Charity charity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sponsor_form_id")
    private Sponsor sponsor_form;

    public boolean isEligibleGiftAid() {
        if (this.ownMoney && this.hasNoBenefitToDonor && this.wishesToGiftAid && isGBResident() && checkPostcode()){
            return true;
        }
        return false;
    }

    public boolean isGBResident(){
        if (this.donor.getAddress().getCountryISOCode().equalsIgnoreCase("gb")) return true;
        return false;
    }

    public boolean checkPostcode(){
        int charCount = 0;
        for (Character c : this.donor.getAddress().getPostcode().toCharArray()) {
            charCount++;
        }

        if (charCount >= 4 && charCount <= 10) {
            return true;
        }
        return false;
    }

    public int getPounds(){
        return this.amountInPence / 100;
    }

    @JsonIgnore
    @Override
    public String getPerson() {
        return this.donor.getFirstName() + " " + this.donor.getLastName();
    }

    @JsonIgnore
    @Override
    public String getEvent() {
        return "Donated: £" + getPounds();
    }

    @JsonIgnore
    @Override
    public Date getDate() {
        return donationDate;
    }
}
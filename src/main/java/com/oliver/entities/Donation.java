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

    public Donation(int amountInPence, boolean ownMoney, boolean hasNoBenefitToDonor, boolean wishesToGiftAid, Donor donor, Charity charity, Sponsor sponsor) {
        this.amountInPence = amountInPence;
        this.ownMoney = ownMoney;
        this.hasNoBenefitToDonor = hasNoBenefitToDonor;
        this.wishesToGiftAid = wishesToGiftAid;
        this.donor = donor;
        this.charity = charity;
        this.sponsor_form = sponsor;
    }

    public Donation(int amountInPence, Boolean ownMoney, boolean hasNoBenefitToDonor, Boolean wishesToGiftAid, Donor donor, Charity charity) {
        this.amountInPence = amountInPence;
        this.ownMoney = ownMoney;
        this.hasNoBenefitToDonor = hasNoBenefitToDonor;
        this.wishesToGiftAid = wishesToGiftAid;
        this.donor = donor;
        this.charity = charity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "amount_in_pence")
    private int amountInPence;

    @Column(name = "donation_date")
    @Temporal(TemporalType.DATE)
    private Date donationDate;

    @Column(name = "is_own_money")
    private boolean ownMoney;

    @Column(name = "has_no_benefit_to_donor")
    private boolean hasNoBenefitToDonor;

    @Column(name = "wishes_to_gift_aid")
    private boolean wishesToGiftAid;

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
        if (this.ownMoney && this.hasNoBenefitToDonor && this.wishesToGiftAid ){
            return true;
        }
        return false;
    }

    @JsonIgnore
    @Override
    public Date getDate() {
        return donationDate;
    }
}
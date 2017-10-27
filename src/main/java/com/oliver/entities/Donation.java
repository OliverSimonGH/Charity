package com.oliver.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "donation")
public class Donation {

    public Donation(int amountInPence, boolean isOwnMoney, boolean hasNoBenefitToDonor, boolean wishesToGiftAid, Donor donor, Charity charity, Sponsor sponsor) {
        this.amountInPence = amountInPence;
        this.isOwnMoney = isOwnMoney;
        this.hasNoBenefitToDonor = hasNoBenefitToDonor;
        this.wishesToGiftAid = wishesToGiftAid;
        this.donor = donor;
        this.charity = charity;
        this.sponsor_form = sponsor;
    }

    public Donation(int amountInPence, Boolean isOwnMoney, boolean hasNoBenefitToDonor, Boolean wishesToGiftAid, Donor donor, Charity charity) {
        this.amountInPence = amountInPence;
        this.isOwnMoney = isOwnMoney;
        this.hasNoBenefitToDonor = hasNoBenefitToDonor;
        this.wishesToGiftAid = wishesToGiftAid;
        this.donor = donor;
        this.charity = charity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int Id;

    @Column(name = "amount_in_pence")
    private int amountInPence;

    @Column(name = "donation_date")
    @Temporal(TemporalType.DATE)
    private Date donationDate;

    @Column(name = "is_own_money")
    private boolean isOwnMoney;

    @Column(name = "has_no_benefit_to_donor")
    private boolean hasNoBenefitToDonor;

    @Column(name = "wishes_to_gift_aid")
    private boolean wishesToGiftAid;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private Donor donor;

    @ManyToOne
    @JoinColumn(name = "charity_id")
    private Charity charity;

    @ManyToOne
    @JoinColumn(name = "sponsor_form_id")
    private Sponsor sponsor_form;

    @PrePersist
    private void generateDates() {
        Calendar date = Calendar.getInstance();
        donationDate = new java.sql.Date(date.getTime().getTime());
    }

}
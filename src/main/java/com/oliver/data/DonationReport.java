package com.oliver.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oliver.entities.Donation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c1633899 on 31/10/2017.
 */
@Data
@NoArgsConstructor
public class DonationReport {

    private int total;
    private int amount;
    private int giftaid;

    @JsonIgnore
    private List<Donation> donations;

    public DonationReport(List<Donation> donations) {
        this.donations = donations;
        this.total = 0;
        this.amount = 0;
        this.giftaid = 0;
        init();
    }

    private void init(){
        calculateAmount();
        calculateGiftAid();
        calculateTotal();
    }

    private void calculateAmount(){
        amount = donations.stream()
                .mapToInt(Donation::getPounds)
                .sum();

    }

    private void calculateGiftAid(){
        giftaid = donations.stream()
                .filter(Donation::isEligibleGiftAid)
                .mapToInt(Donation::getPounds)
                .sum();
    }

    private void calculateTotal(){
        total = amount + giftaid;
    }
}

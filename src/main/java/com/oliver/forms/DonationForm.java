package com.oliver.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonationForm {

    private int amountInPence;
    private boolean isOwnMoney;
    private boolean hasNoBenefitToDonor;
    private boolean wishesToGiftAid;

}

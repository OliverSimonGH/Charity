package com.oliver.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by c1633899 on 31/10/2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonationReport {

    private int total;
    private int amount;
    private int giftaid;
}

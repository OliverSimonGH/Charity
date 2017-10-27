package com.oliver.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressForm {

    private String buildingName;
    private int buildingNumber;
    private String street;
    private String district;
    private String city;
    private String postcode;
    private String countryISOCode;
}

package com.oliver.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SponsorForm {

    private String furl;
    private String fundraiserName;
    private String fundraisingAction;
}

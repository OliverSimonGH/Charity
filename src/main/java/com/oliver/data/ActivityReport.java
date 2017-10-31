package com.oliver.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by c1633899 on 31/10/2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityReport {
    private String person;
    private String event;
    private Date date;
}

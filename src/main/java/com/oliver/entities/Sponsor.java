package com.oliver.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sponsor_form")
public class Sponsor {

    public Sponsor(String fundraiserName, String fundraisingAction, String furl) {
        this.fundraiserName = fundraiserName;
        this.fundraisingAction = fundraisingAction;
        this.furl = furl;
    }

    public Sponsor(String fundraiserName, String fundraisingAction, String furl, Charity charity) {
        this.fundraiserName = fundraiserName;
        this.fundraisingAction = fundraisingAction;
        this.furl = furl;
        this.charity = charity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int Id;

    @Column(name = "fundraiser_name")
    private String fundraiserName;

    @Column(name = "fundraising_action")
    private String fundraisingAction;

    @Column(name = "date_created")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;

    @Column(name = "first_valid_day")
    @Temporal(TemporalType.DATE)
    private Date firstValidDay;

    @Column(name = "last_valid_day")
    @Temporal(TemporalType.DATE)
    private Date lastValidDay;

    @Column(name = "furl")
    private String furl;

    @ManyToOne
    @JoinColumn(name = "charity_id")
    private Charity charity;

    @OneToMany(mappedBy = "sponsor_form", cascade = CascadeType.ALL)
    private List<Donation> donations;

    @PrePersist
    private void generateDates() {
        Calendar date = Calendar.getInstance();
        dateCreated = firstValidDay = new java.sql.Date(date.getTime().getTime());
        date.add(Calendar.MONTH, 1);
        lastValidDay = new java.sql.Date(date.getTime().getTime());
    }
}


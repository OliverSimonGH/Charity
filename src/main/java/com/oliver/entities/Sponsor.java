package com.oliver.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sponsor_form")
public class Sponsor implements ActivityInterface{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Id;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "charity_id")
    private Charity charity;

    @JsonIgnore
    @OneToMany(mappedBy = "sponsor_form", cascade = CascadeType.ALL)
    private List<Donation> donations;

    @JsonIgnore
    @Override
    public String getPerson() {
        return this.fundraiserName;
    }

    @JsonIgnore
    @Override
    public String getEvent() {
        return "Created Sponsor Form";
    }

    @JsonIgnore
    @Override
    public Date getDate() {
        return dateCreated;
    }
}


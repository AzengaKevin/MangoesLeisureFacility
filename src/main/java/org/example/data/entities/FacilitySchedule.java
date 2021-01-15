package org.example.data.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "facilities_schedule")
public class FacilitySchedule extends Schedule {

    private String activities;

    @ManyToOne
    @JoinColumn(name = "facility_id")
    private Facility facility;

    public FacilitySchedule() {
    }

    public FacilitySchedule(Date date, Staff staff, String activities, Facility facility) {
        super(date, staff);
        this.activities = activities;
        this.facility = facility;
    }

    public FacilitySchedule(Integer id, Date date, Staff staff, String activities, Facility facility) {
        super(id, date, staff);
        this.activities = activities;
        this.facility = facility;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }
}

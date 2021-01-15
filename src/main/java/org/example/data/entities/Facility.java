package org.example.data.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "facilities")
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String activities;

    @OneToMany(mappedBy = "facility")
    private List<FacilitySchedule> facilitySchedules = new ArrayList<>();

    public Facility() {
    }

    public Facility(String name, String activities) {
        this.name = name;
        this.activities = activities;
    }

    public Facility(Integer id, String name, String activities) {
        this.id = id;
        this.name = name;
        this.activities = activities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public List<FacilitySchedule> getFacilitySchedules() {
        return facilitySchedules;
    }
}

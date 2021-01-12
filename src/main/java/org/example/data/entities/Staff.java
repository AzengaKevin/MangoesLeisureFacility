package org.example.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "staffs")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "part_time")
    private Boolean partTime;
    private Boolean temporary;
    private Boolean trained;

    @Column(name = "title")
    @Enumerated(EnumType.ORDINAL)
    private StaffTitle staffTitle;

    public Staff() {
    }

    public Staff(String name, Boolean partTime, Boolean temporary, Boolean trained, StaffTitle staffTitle) {
        this.name = name;
        this.partTime = partTime;
        this.temporary = temporary;
        this.trained = trained;
        this.staffTitle = staffTitle;
    }

    public Staff(Integer id, String name, Boolean partTime, Boolean temporary, Boolean trained, StaffTitle staffTitle) {
        this.id = id;
        this.name = name;
        this.partTime = partTime;
        this.temporary = temporary;
        this.trained = trained;
        this.staffTitle = staffTitle;
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

    public Boolean getPartTime() {
        return partTime;
    }

    public void setPartTime(Boolean partTime) {
        this.partTime = partTime;
    }

    public Boolean getTemporary() {
        return temporary;
    }

    public void setTemporary(Boolean temporary) {
        this.temporary = temporary;
    }

    public Boolean getTrained() {
        return trained;
    }

    public void setTrained(Boolean trained) {
        this.trained = trained;
    }

    public StaffTitle getStaffTitle() {
        return staffTitle;
    }

    public void setStaffTitle(StaffTitle staffTitle) {
        this.staffTitle = staffTitle;
    }
}

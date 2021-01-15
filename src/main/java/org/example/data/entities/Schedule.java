package org.example.data.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "schedule")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Basic
    protected Date date;
    @ManyToOne
    @JoinColumn(name = "staff_id")
    protected Staff staff;

    public Schedule() {
    }

    public Schedule(Date date, Staff staff) {
        this.date = date;
        this.staff = staff;
    }

    public Schedule(Integer id, Date date, Staff staff) {
        this.id = id;
        this.date = date;
        this.staff = staff;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}

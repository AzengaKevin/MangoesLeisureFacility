package org.example.data.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    private Boolean paid;

    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @Basic
    Timestamp updatedAt;

    public Attendance() {
    }

    public Attendance(Member member, Schedule schedule, Boolean paid, Timestamp createdAt, Timestamp updatedAt) {
        this.member = member;
        this.schedule = schedule;
        this.paid = paid;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Attendance(Long id, Member member, Schedule schedule, Boolean paid, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.member = member;
        this.schedule = schedule;
        this.paid = paid;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}

package org.example.data.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Time;


@Entity
@Table(name = "classes_schedule")
public class ClassSchedule extends Schedule {

    private Integer grade;
    @Basic
    @Column(name = "start_time")
    private Time startTime;
    private Integer duration;
    @Column(name = "total_vacancies")
    private Integer totalVacancies;

    public ClassSchedule() {
    }

    public ClassSchedule(Date date, Staff staff, Integer grade, Time startTime, Integer duration, Integer totalVacancies) {
        super(date, staff);
        this.grade = grade;
        this.startTime = startTime;
        this.duration = duration;
        this.totalVacancies = totalVacancies;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getTotalVacancies() {
        return totalVacancies;
    }

    public void setTotalVacancies(Integer totalVacancies) {
        this.totalVacancies = totalVacancies;
    }
}

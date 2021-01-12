package org.example.data.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "memberships")
@NamedQueries({
        @NamedQuery(name = "Membership.findByName", query = "SELECT m FROM Membership m WHERE m.name = :name"),
        @NamedQuery(name = "Membership.findAll", query = "SELECT m FROM Membership m")
})
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String name;
    private Double fee;

    @OneToMany(mappedBy = "membership", cascade = CascadeType.ALL)
    private List<Member> members = new ArrayList<>();

    public Membership() {
    }

    public Membership(String name, Double fee) {
        this.name = name;
        this.fee = fee;
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

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}

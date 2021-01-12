package org.example.data.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "members")
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phone;
    private Integer grade;
    @Column(name = "bank_account_number")
    private Long bankAccountNumber;
    @ManyToOne
    @JoinColumn(name = "membership_id")
    private Membership membership;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Member() {
    }

    public Member(String name, String email, String phone, Integer grade, Long bankAccountNumber, Membership membership, Category category) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.grade = grade;
        this.bankAccountNumber = bankAccountNumber;
        this.membership = membership;
        this.category = category;
    }

    public Member(Integer id, String name, String email, String phone, Integer grade, Long bankAccountNumber, Membership membership, Category category) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.grade = grade;
        this.bankAccountNumber = bankAccountNumber;
        this.membership = membership;
        this.category = category;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Long getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(Long bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

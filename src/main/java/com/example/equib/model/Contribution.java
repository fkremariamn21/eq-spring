package com.example.equib.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Contribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private LocalDate date;

    // Many-to-One relationship with Member
    // 'fetch = FetchType.LAZY' is a performance optimization; the member data is loaded only when explicitly requested.
    // 'JoinColumn' specifies the foreign key column in the 'contribution' table.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Contribution() {}

    public Contribution(Double amount, LocalDate date, Member member) {
        this.amount = amount;
        this.date = date;
        this.member = member;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }
}
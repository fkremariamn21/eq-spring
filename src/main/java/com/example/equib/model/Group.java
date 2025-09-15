package com.example.equib.model;

import jakarta.persistence.*;
import java.util.*;

@Entity 
@Table(name = "groups") // avoid conflict with SQL reserved word
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String name;
    private Double contributionAmount;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Member> members = new ArrayList<>();

    public Group() {}

    public Group(String name, Double contributionAmount) {
        this.name = name;
        this.contributionAmount = contributionAmount;
    } 

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getContributionAmount() { return contributionAmount; }
    public void setContributionAmount(Double contributionAmount) { this.contributionAmount = contributionAmount; }

    public List<Member> getMembers() { return members; }
    public void setMembers(List<Member> members) { this.members = members; }
}

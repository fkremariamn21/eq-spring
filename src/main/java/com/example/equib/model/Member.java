package com.example.equib.model;
import jakarta.persistence.*;

@Entity
public class Member { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName; 

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public Member() {}

    public Member(String fullName, Group group) {
        this.fullName = fullName;
        this.group = group;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public Group getGroup() { return group; }
    public void setGroup(Group group) { this.group = group; }
}

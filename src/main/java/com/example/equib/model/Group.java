package com.example.equib.model;

import jakarta.persistence.*;
import java.util.*;
import java.time.LocalDate;

@Entity
@Table(name = "groups") // Using "groups" to avoid conflict with SQL reserved word
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100) // Ensure group name is not null and has a reasonable length
    private String name;

    @Column(length = 255) // Optional description field
    private String description;

    @Column(name = "contribution_amount") // Clear naming for contribution amount
    private Double contributionAmount;

    @Enumerated(EnumType.STRING) // Store status as a string (e.g., "ACTIVE", "INACTIVE")
    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(50) DEFAULT 'ACTIVE'") // Default status
    private GroupStatus status = GroupStatus.ACTIVE; // Default to ACTIVE

    @Column(name = "creation_date", nullable = false, updatable = false) // Date when the group was created
    private LocalDate creationDate;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    // FetchType.LAZY is generally good for collections to avoid loading them unnecessarily
    private List<Member> members = new ArrayList<>();

    // --- Constructors ---
    public Group() {
        // Default constructor needed by JPA
    }

    public Group(String name, String description, Double contributionAmount) {
        this.name = name;
        this.description = description;
        this.contributionAmount = contributionAmount;
        this.creationDate = LocalDate.now(); // Set creation date automatically
        this.status = GroupStatus.ACTIVE; // Set default status
    }

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getContributionAmount() {
        return contributionAmount;
    }

    public void setContributionAmount(Double contributionAmount) {
        this.contributionAmount = contributionAmount;
    }

    public GroupStatus getStatus() {
        return status;
    }

    public void setStatus(GroupStatus status) {
        this.status = status;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    // --- Helper methods for managing members ---
    public void addMember(Member member) {
        members.add(member);
        member.setGroup(this);
    }

    public void removeMember(Member member) {
        members.remove(member);
        member.setGroup(null); 
    }

    @Override
    public String toString() { 
        return "Group{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", contributionAmount=" + contributionAmount +
               ", status=" + status +
               ", creationDate=" + creationDate +
               ", memberCount=" + members.size() + // Show member count instead of full list
               '}';
    }
}
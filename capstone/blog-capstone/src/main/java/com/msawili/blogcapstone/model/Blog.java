package com.msawili.blogcapstone.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "BLOG")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "BODY")
    @Lob
    private String body;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "LAST_UPDATE")
    private LocalDateTime lastUpdate;

    @PrePersist
    public void setInitialTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        lastUpdate = now;
    }

    @PreUpdate
    public void setLastUpdate() {
        lastUpdate = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "BLOGGER_ID")
    private Blogger blogger;

}

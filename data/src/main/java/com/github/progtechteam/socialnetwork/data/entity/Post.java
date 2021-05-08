package com.github.progtechteam.socialnetwork.data.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Evgenii Puliaev
 */
@Data
@Entity
@Table(name = "posts", schema = "entities")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", updatable = false)
    private Integer id;

    @Column(nullable = false)
    private String payload;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false, updatable = false)
    private User author;

    @ManyToMany(mappedBy = "posts")
    private Set<User> userPostOwners = new HashSet<>();

    @ManyToMany(mappedBy = "posts")
    private Set<Community> communityPostOwners = new HashSet<>();

}

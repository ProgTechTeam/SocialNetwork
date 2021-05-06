package com.github.progtechteam.socialnetwork.data.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Evgenii Puliaev
 */
@Data
@Entity
@Table(name = "communities", schema = "entities")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_id", updatable = false)
    private Integer id;

    @Column(length = 32, nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "communities_posts",
            schema = "relationships",
            joinColumns = @JoinColumn(
                    name = "community_id",
                    referencedColumnName = "community_id",
                    nullable = false
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "post_id",
                    referencedColumnName = "post_id",
                    nullable = false
            )
    )
    private Set<Post> posts = new HashSet<>();

    @ManyToMany(mappedBy = "subscriptionsOnCommunities")
    private Set<User> subscribers = new HashSet<>();

    public void addPost(Post post) {
        post.getCommunityPostOwners().add(this);
        posts.add(post);
    }

    public void removePost(Post post) {
        post.getCommunityPostOwners().remove(this);
        posts.remove(post);
    }
}

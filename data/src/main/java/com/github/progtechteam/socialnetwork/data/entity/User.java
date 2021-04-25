package com.github.progtechteam.socialnetwork.data.entity;

import com.github.progtechteam.socialnetwork.data.entity.chat.GroupChat;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Evgenii Puliaev
 */
@Data
@Entity
@Table(name = "users", schema = "entities")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false)
    private Integer id;

    @Column(nullable = false, length = 32)
    private String firstName;

    @Column(nullable = false, length = 32)
    private String lastName;

    @OneToOne(mappedBy = "accountOwner", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private Account account;

    @ManyToMany(mappedBy = "subscriptionsOnUsers")
    private Set<User> subscribers = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "subscriptions_on_users",
            schema = "relationships",
            joinColumns = @JoinColumn(
                    name = "subscriber",
                    referencedColumnName = "user_id",
                    nullable = false
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "user_to_follow",
                    referencedColumnName = "user_id",
                    nullable = false
            )
    )
    private Set<User> subscriptionsOnUsers = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "subscriptions_on_communities",
            schema = "relationships",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "user_id",
                    nullable = false
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "community_id",
                    referencedColumnName = "community_id",
                    nullable = false
            )
    )
    private Set<Community> subscriptionsOnCommunities = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "users_group_chats",
            schema = "relationships",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "user_id",
                    nullable = false
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "chat_id",
                    referencedColumnName = "chat_id",
                    nullable = false
            )
    )
    private Set<GroupChat> groupChats = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "users_posts",
            schema = "relationships",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "user_id",
                    nullable = false
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "post_id",
                    referencedColumnName = "post_id",
                    nullable = false
            )
    )
    private Set<Post> posts = new HashSet<>();

    public void subscribeToUser(User userToFollow) {
        subscriptionsOnUsers.add(userToFollow);
        userToFollow.subscribers.add(this);
    }

    public void unsubscribeFromUser(User userToUnsubscribe) {
        subscriptionsOnUsers.remove(userToUnsubscribe);
        userToUnsubscribe.subscribers.remove(this);
    }

    public void subscribeToCommunity(Community community) {
        community.getSubscribers().add(this);
        subscriptionsOnCommunities.add(community);
    }

    public void unsubscribeFromCommunity(Community community) {
        community.getSubscribers().remove(this);
        subscriptionsOnCommunities.remove(community);
    }

    public void enterGroupChat(GroupChat chat) {
        chat.getParticipants().add(this);
        groupChats.add(chat);
    }

    public void quitGroupChat(GroupChat chat) {
        chat.getParticipants().remove(this);
        groupChats.remove(chat);
    }

    public void addPost(Post post) {
        post.getUserPostOwners().add(this);
        posts.add(post);
    }

    public void removePost(Post post) {
        post.getUserPostOwners().remove(this);
        posts.remove(post);
    }

    public Set<User> getSubscribers() {
        return subscribers.stream()
                .filter(s -> !subscriptionsOnUsers.contains(s))
                .collect(Collectors.toUnmodifiableSet());
    }

    public Set<User> getSubscriptionsOnUsers() {
        return subscriptionsOnUsers.stream()
                .filter(s -> !subscribers.contains(s))
                .collect(Collectors.toUnmodifiableSet());
    }

    public Set<User> getFriends() {
        return subscriptionsOnUsers.stream()
                .filter(s -> subscribers.contains(s))
                .collect(Collectors.toUnmodifiableSet());
    }
}

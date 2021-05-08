package com.github.progtechteam.socialnetwork.data.entity;

import com.github.progtechteam.socialnetwork.commons.Role;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Evgenii Puliaev
 */
@Data
@Entity
@Table(name = "accounts", schema = "entities")
public class Account {

    @Id
    @Column(name = "account_owner_id")
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "account_owner_id", nullable = false, updatable = false)
    private User accountOwner;

    @Column(length = 32, unique = true, nullable = false)
    private String email;

    @Column(nullable = false, length = 60, columnDefinition = "bpchar")
    private String password;

    @Column(name = "role", nullable = false)
    private Role role;

    @Column
    private boolean isDeleted;

    @Column
    private boolean isBlocked;

}

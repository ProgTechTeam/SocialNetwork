package com.github.progtechteam.socialnetwork.data.entity;

import com.github.progtechteam.socialnetwork.commons.ComplaintType;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Burdyug Pavel
 */
@Data
@Entity
@Table(name ="complaints_on_posts" , schema = "relationships")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complaint_id", updatable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false, updatable = false)
    private Post post;

    @Column(name = "complaint_type", nullable = false)
    private ComplaintType complaintType;

}

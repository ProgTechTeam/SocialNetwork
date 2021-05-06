package com.github.progtechteam.socialnetwork.data.entity;

import com.github.progtechteam.socialnetwork.commons.TestType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Evgenii Puliaev
 */
@Entity
@Table(schema = "test", name = "tests")
@Data
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "type")
    private TestType type;

}

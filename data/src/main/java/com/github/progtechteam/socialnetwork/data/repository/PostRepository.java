package com.github.progtechteam.socialnetwork.data.repository;

import com.github.progtechteam.socialnetwork.data.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Evgenii Puliaev
 */
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("from Post p join p.userPostOwners po where po.id = :ownerId")
    List<Post> findAllByUserPostOwnersContains(@Param("ownerId") Integer ownerId);

}

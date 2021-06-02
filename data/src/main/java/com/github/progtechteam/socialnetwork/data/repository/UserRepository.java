package com.github.progtechteam.socialnetwork.data.repository;

import com.github.progtechteam.socialnetwork.data.entity.Post;
import com.github.progtechteam.socialnetwork.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Evgenii Puliaev
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("from User u join u.likedPosts lp where lp.id = :postId")
    List<User> findAllLikedUsersByPostId(@Param("postId") Integer postId);
}

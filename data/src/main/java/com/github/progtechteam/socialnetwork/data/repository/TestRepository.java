package com.github.progtechteam.socialnetwork.data.repository;

import com.github.progtechteam.socialnetwork.data.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Evgenii Puliaev
 */
@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {

}

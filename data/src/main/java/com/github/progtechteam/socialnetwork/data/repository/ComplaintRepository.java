package com.github.progtechteam.socialnetwork.data.repository;

import com.github.progtechteam.socialnetwork.data.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Burdyug Pavel
 */
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
}

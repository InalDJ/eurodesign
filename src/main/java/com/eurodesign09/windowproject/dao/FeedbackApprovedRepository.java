package com.eurodesign09.windowproject.dao;

import com.eurodesign09.windowproject.entity.FeedbackApproved;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackApprovedRepository extends JpaRepository<FeedbackApproved, Integer> {
}

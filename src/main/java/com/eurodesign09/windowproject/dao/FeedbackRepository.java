package com.eurodesign09.windowproject.dao;

import com.eurodesign09.windowproject.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {


}

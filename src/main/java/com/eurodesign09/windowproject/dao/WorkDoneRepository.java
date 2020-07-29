package com.eurodesign09.windowproject.dao;

import com.eurodesign09.windowproject.entity.WorkDone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkDoneRepository extends JpaRepository<WorkDone, Integer> {
}

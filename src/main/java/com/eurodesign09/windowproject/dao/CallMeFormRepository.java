package com.eurodesign09.windowproject.dao;

import com.eurodesign09.windowproject.entity.CallMeForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallMeFormRepository extends JpaRepository<CallMeForm, Integer> {
}

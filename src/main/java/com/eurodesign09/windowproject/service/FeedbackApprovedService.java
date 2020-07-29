package com.eurodesign09.windowproject.service;

import com.eurodesign09.windowproject.dao.FeedbackApprovedRepository;
import com.eurodesign09.windowproject.entity.FeedbackApproved;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackApprovedService {


    private FeedbackApprovedRepository feedbackApprovedRepository;

    @Autowired
    public FeedbackApprovedService(FeedbackApprovedRepository feedbackApprovedRepository) {
        this.feedbackApprovedRepository = feedbackApprovedRepository;
    }

    public List<FeedbackApproved> listAllApprovedFeedback() {
        return feedbackApprovedRepository.findAll();
    }

    public void saveApprovedFeedback (FeedbackApproved feedbackApproved) {
        feedbackApprovedRepository.save(feedbackApproved);
    }

    public FeedbackApproved getApprovedFeedback (Integer id) {
        return feedbackApprovedRepository.findById(id).get();
    }

    public void deleteApprovedFeedback (Integer id) {
        feedbackApprovedRepository.deleteById(id);
    }
}

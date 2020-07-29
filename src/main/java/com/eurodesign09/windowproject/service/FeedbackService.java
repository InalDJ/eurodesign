package com.eurodesign09.windowproject.service;

import com.eurodesign09.windowproject.dao.FeedbackRepository;
import com.eurodesign09.windowproject.entity.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OptionalDataException;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {


    private FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public List<Feedback> listAllFeedback() {
        return feedbackRepository.findAll();
    }

    public void saveFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    public Feedback getFeedback(Integer id) {
        return feedbackRepository.findById(id).get();
    }

    public void deleteFeedback(Integer id) {
        feedbackRepository.deleteById(id);
    }
}

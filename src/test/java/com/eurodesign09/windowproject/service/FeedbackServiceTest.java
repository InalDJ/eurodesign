package com.eurodesign09.windowproject.service;

import com.eurodesign09.windowproject.dao.FeedbackRepository;
import com.eurodesign09.windowproject.entity.Feedback;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


class FeedbackServiceTest {

    private FeedbackService feedbackService;

    @Mock
    private FeedbackRepository feedbackRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        feedbackService = new FeedbackService(feedbackRepository);
    }

    @Test
    void shouldListAllTest() {
        Feedback feedback1 = new Feedback(1,"John Jones", "Amazing! I really like it!");
        Feedback feedback2 = new Feedback(2,"Isac Clarke", "Mediocre! I don't like the product!");
        Feedback feedback3 = new Feedback(3,"Junior Dos Santos", "Good! In Brazil we like this kind of product!");


        List<Feedback> feedbackList = new ArrayList<>();
        feedbackList.add(feedback1);
        feedbackList.add(feedback2);
        feedbackList.add(feedback3);

        given(feedbackRepository.findAll()).willReturn(feedbackList);

        List<Feedback> listFeedback = feedbackService.listAllFeedback();

        assertThat(listFeedback).hasSize(3);

        assertThat(listFeedback.get(0).getFeedback_id()).isEqualTo(1);
        assertThat(listFeedback.get(1).getFeedback_id()).isEqualTo(2);
        assertThat(listFeedback.get(2).getFeedback_id()).isEqualTo(3);

        assertThat(listFeedback.get(0).getFeedbackName()).isEqualTo("John Jones");
        assertThat(listFeedback.get(1).getFeedbackName()).isEqualTo("Isac Clarke");
        assertThat(listFeedback.get(2).getFeedbackName()).isEqualTo("Junior Dos Santos");

        assertThat(listFeedback.get(0).getFeedbackText()).isEqualTo("Amazing! I really like it!");
        assertThat(listFeedback.get(1).getFeedbackText()).isEqualTo("Mediocre! I don't like the product!");
        assertThat(listFeedback.get(2).getFeedbackText()).isEqualTo("Good! In Brazil we like this kind of product!");

    }

    @Test
    void shouldSaveFeedbackTest() {
        Feedback newFeedback = new Feedback();
        newFeedback.setFeedback_id(22);
        newFeedback.setFeedbackName("Petra");
        newFeedback.setFeedbackText("Petrova");

        given(feedbackRepository.save(any())).willReturn(newFeedback);

        ArgumentCaptor<Feedback> captor = ArgumentCaptor.forClass(Feedback.class);

        feedbackService.saveFeedback(newFeedback);

        verify(feedbackRepository).save(captor.capture());

        Feedback capturedFeedback = captor.getValue();

        assertThat(capturedFeedback.getFeedbackName()).isEqualTo("Petra");
        assertThat(capturedFeedback.getFeedbackText()).isEqualTo("Petrova");
        assertThat(capturedFeedback.getFeedback_id()).isEqualTo(22);

    }

    @Test
    void shouldGetFeedbackTest() {
        int feedbackId = 1;
        Feedback feedback = new Feedback(feedbackId,"John Jones", "Amazing! I really like it!");

        given(feedbackRepository.findById(feedbackId)).willReturn(Optional.of(feedback));

        Feedback feedbackFound = feedbackService.getFeedback(feedbackId);

        assertThat(feedbackFound).isNotNull();

        assertThat(feedbackFound.getFeedback_id()).isEqualTo(feedbackId);
        assertThat(feedbackFound.getFeedbackName()).isEqualTo("John Jones");
        assertThat(feedbackFound.getFeedbackText()).isEqualTo("Amazing! I really like it!");
    }

    @Test
    void shouldDeleteFeedbackTest() {
        int feedbackId = 1;
        Feedback feedback = new Feedback(feedbackId,"John Jones", "Amazing! I really like it!");
        given(feedbackRepository.findById(feedbackId)).willReturn(Optional.of(feedback));
        doNothing().when(feedbackRepository).deleteById(feedbackId);

        feedbackService.deleteFeedback(feedbackId);
        Feedback feedbackObtained = feedbackService.getFeedback(feedbackId);

        String message = null;
        if(feedbackObtained != null){
            message = "The item has been deleted!";
            assertThat(message).isNotNull();
        } else{
            assertThat(message).isNull();
        }
    }
}
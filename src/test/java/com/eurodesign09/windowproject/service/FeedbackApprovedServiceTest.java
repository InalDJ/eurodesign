package com.eurodesign09.windowproject.service;

import com.eurodesign09.windowproject.dao.FeedbackApprovedRepository;
import com.eurodesign09.windowproject.entity.FeedbackApproved;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

class FeedbackApprovedServiceTest {

    @Mock
    private FeedbackApprovedRepository feedbackApprovedRepository;

    private FeedbackApprovedService feedbackApprovedService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        feedbackApprovedService = new FeedbackApprovedService(feedbackApprovedRepository);
    }

    @Test
    void shouldListAllTest() {
        FeedbackApproved feedbackApproved1 = new FeedbackApproved(1,"John Jones", "Amazing! I really like it!", new Date());
        FeedbackApproved feedbackApproved2 = new FeedbackApproved(2,"Isac Clarke", "Mediocre! I don't like the product!", new Date());
        FeedbackApproved feedbackApproved3 = new FeedbackApproved(3,"Junior Dos Santos", "Good! In Brazil we like this kind of product!", new Date());


        List<FeedbackApproved> feedbackApprovedList = new ArrayList<>();
        feedbackApprovedList.add(feedbackApproved1);
        feedbackApprovedList.add(feedbackApproved2);
        feedbackApprovedList.add(feedbackApproved3);

        given(feedbackApprovedRepository.findAll()).willReturn(feedbackApprovedList);

        List<FeedbackApproved> listApprovedFeedback = feedbackApprovedService.listAllApprovedFeedback();

        assertThat(listApprovedFeedback).hasSize(3);

        assertThat(listApprovedFeedback.get(0).getFeedbackAppId()).isEqualTo(1);
        assertThat(listApprovedFeedback.get(1).getFeedbackAppId()).isEqualTo(2);
        assertThat(listApprovedFeedback.get(2).getFeedbackAppId()).isEqualTo(3);


        assertThat(listApprovedFeedback.get(0).getFeedbackAppName()).isEqualTo("John Jones");
        assertThat(listApprovedFeedback.get(1).getFeedbackAppName()).isEqualTo("Isac Clarke");
        assertThat(listApprovedFeedback.get(2).getFeedbackAppName()).isEqualTo("Junior Dos Santos");

        assertThat(listApprovedFeedback.get(0).getFeedbackAppText()).isEqualTo("Amazing! I really like it!");
        assertThat(listApprovedFeedback.get(1).getFeedbackAppText()).isEqualTo("Mediocre! I don't like the product!");
        assertThat(listApprovedFeedback.get(2).getFeedbackAppText()).isEqualTo("Good! In Brazil we like this kind of product!");

        assertThat(listApprovedFeedback.get(0).getFeedbackAppDate()).isEqualTo(feedbackApproved1.getFeedbackAppDate());
        assertThat(listApprovedFeedback.get(1).getFeedbackAppDate()).isEqualTo(feedbackApproved2.getFeedbackAppDate());
        assertThat(listApprovedFeedback.get(2).getFeedbackAppDate()).isEqualTo(feedbackApproved3.getFeedbackAppDate());
    }

    @Test
    void shouldSaveFeedbackTest() {
        FeedbackApproved newApprovedFeedback = new  FeedbackApproved();
        newApprovedFeedback.setFeedbackAppId(22);
        newApprovedFeedback.setFeedbackAppName("Petra");
        newApprovedFeedback.setFeedbackAppText("Petrova");
        newApprovedFeedback.setFeedbackAppDate(new Date());

        given(feedbackApprovedRepository.save(any())).willReturn(newApprovedFeedback);

        ArgumentCaptor< FeedbackApproved> captor = ArgumentCaptor.forClass( FeedbackApproved.class);

        feedbackApprovedService.saveApprovedFeedback(newApprovedFeedback);

        verify(feedbackApprovedRepository).save(captor.capture());

        FeedbackApproved capturedFeedback = captor.getValue();

        assertThat(capturedFeedback.getFeedbackAppDate()).isEqualTo(newApprovedFeedback.getFeedbackAppDate());
        assertThat(capturedFeedback.getFeedbackAppName()).isEqualTo("Petra");
        assertThat(capturedFeedback.getFeedbackAppText()).isEqualTo("Petrova");
        assertThat(capturedFeedback.getFeedbackAppId()).isEqualTo(22);



    }

    @Test
    void shouldGetFeedbackTest() {
        int feedbackApprovedId = 1;
        FeedbackApproved feedbackApproved1 = new FeedbackApproved(
                feedbackApprovedId,"John Jones", "Amazing! I really like it!", new Date());

        given(feedbackApprovedRepository.findById(feedbackApprovedId)).willReturn(Optional.of(feedbackApproved1));

        FeedbackApproved feedbackFound = feedbackApprovedService.getApprovedFeedback(feedbackApprovedId);

        assertThat(feedbackFound).isNotNull();

        assertThat(feedbackFound.getFeedbackAppId()).isEqualTo(feedbackApprovedId);
        assertThat(feedbackFound.getFeedbackAppName()).isEqualTo("John Jones");
        assertThat(feedbackFound.getFeedbackAppText()).isEqualTo("Amazing! I really like it!");
        assertThat(feedbackFound.getFeedbackAppDate()).isEqualTo(feedbackApproved1.getFeedbackAppDate());

    }

    @Test
    void shouldDeleteFeedbackTest() {
        int feedbackApprovedId = 1;
        FeedbackApproved feedbackApproved = new FeedbackApproved(
                feedbackApprovedId,"John Jones", "Amazing! I really like it!", new Date());
        given(feedbackApprovedRepository.findById(feedbackApprovedId)).willReturn(Optional.of(feedbackApproved));
        doNothing().when(feedbackApprovedRepository).deleteById(feedbackApprovedId);

        feedbackApprovedService.deleteApprovedFeedback(feedbackApprovedId);
        FeedbackApproved obtainedFeedback = feedbackApprovedService.getApprovedFeedback(feedbackApprovedId);
        String message = null;
        if(obtainedFeedback != null){
            message = "The item has been deleted!";
            assertThat(message).isNotNull();
        } else{
            assertThat(message).isNull();
        }
    }
}
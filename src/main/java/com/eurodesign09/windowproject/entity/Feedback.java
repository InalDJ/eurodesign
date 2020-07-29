package com.eurodesign09.windowproject.entity;

import javax.persistence.*;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Integer feedbackId;
    @Column(name = "feedback_name")
    private String feedbackName;
    @Column(name = "feedback_text")
    private String feedbackText;

    public Feedback() {
    }

    public Feedback(Integer feedbackId, String feedbackName, String feedbackText) {
        this.feedbackId = feedbackId;
        this.feedbackName = feedbackName;
        this.feedbackText = feedbackText;
    }

    public Integer getFeedback_id() {
        return feedbackId;
    }

    public void setFeedback_id(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFeedbackName() {
        return feedbackName;
    }

    public void setFeedbackName(String feedbackName) {
        this.feedbackName = feedbackName;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }
}

package com.eurodesign09.windowproject.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "feedback_approved")
public class FeedbackApproved {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_app_id")
    private Integer feedbackAppId;
    @Column(name = "feedback_app_name")
    private String feedbackAppName;
    @Column(name = "feedback_app_text")
    private String feedbackAppText;
    @Column(name = "feedback_app_date")
    private Date feedbackAppDate;

    public FeedbackApproved() {
    }


    public FeedbackApproved(Integer feedbackAppId, String feedbackAppName, String feedback_app_text, Date feedbackAppDate) {
        this.feedbackAppId = feedbackAppId;
        this.feedbackAppName = feedbackAppName;
        this.feedbackAppText = feedback_app_text;
        this.feedbackAppDate = feedbackAppDate;
    }

    public Integer getFeedbackAppId() {
        return feedbackAppId;
    }

    public void setFeedbackAppId(Integer feedbackAppId) {
        this.feedbackAppId = feedbackAppId;
    }

    public String getFeedbackAppName() {
        return feedbackAppName;
    }

    public void setFeedbackAppName(String feedbackAppName) {
        this.feedbackAppName = feedbackAppName;
    }

    public String getFeedbackAppText() {
        return feedbackAppText;
    }

    public void setFeedbackAppText(String feedbackAppText) {
        this.feedbackAppText = feedbackAppText;
    }

    public Date getFeedbackAppDate() {
        return feedbackAppDate;
    }

    public void setFeedbackAppDate(Date feedbackAppDate) {
        this.feedbackAppDate = feedbackAppDate;
    }
}

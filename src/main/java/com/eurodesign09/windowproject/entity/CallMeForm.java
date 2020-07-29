package com.eurodesign09.windowproject.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "call_me_form")
public class CallMeForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Integer clientId;
    @Column(name = "client_name")
    private String clientName;
    @Column(name = "client_phone")
    private String clientPhone;
    @Column(name = "client_date")
    private Date clientDate;

    public CallMeForm() {
    }

    public CallMeForm(Integer clientId, String clientName, String clientPhone, Date clientDate) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.clientDate = clientDate;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public Date getClientDate() {
        return clientDate;
    }

    public void setClientDate(Date clientDate) {
        this.clientDate = clientDate;
    }
}

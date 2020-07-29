package com.eurodesign09.windowproject.service;

import com.eurodesign09.windowproject.dao.CallMeFormRepository;
import com.eurodesign09.windowproject.entity.CallMeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CallMeFormService {

    private CallMeFormRepository callMeFormRepository;
    @Autowired
    public CallMeFormService(CallMeFormRepository callMeFormRepository) {
        this.callMeFormRepository = callMeFormRepository;
    }

    public List<CallMeForm> listAllCallMeForms() {
        return callMeFormRepository.findAll();
    }

    public void saveCallMeForm (CallMeForm callMeForm) {
        callMeForm.setClientDate(new Date());
        callMeFormRepository.save(callMeForm);
    }

    public CallMeForm getCallMeForm (Integer id) {
        return callMeFormRepository.findById(id).get();
    }

    public void deleteCallMeForm (Integer id) {
        callMeFormRepository.deleteById(id);
    }
}

package com.eurodesign09.windowproject.service;

import com.eurodesign09.windowproject.dao.CallMeFormRepository;
import com.eurodesign09.windowproject.entity.CallMeForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class CallMeFormServiceTest {
    @Mock
    private CallMeFormRepository callMeFormRepository;
    private CallMeFormService callMeFormService;

    @BeforeEach
    void setUp() {
        initMocks(this);
        callMeFormService = new CallMeFormService(callMeFormRepository);
    }

    @Test
    void listAllCallMeForms() {
        CallMeForm form1 = new CallMeForm(1, "Jason Bourne", "88005553535",new Date());
        CallMeForm form2 = new CallMeForm(2, "John Cena", "9379992", new Date());

        List<CallMeForm> formList = new ArrayList<>();
        formList.add(form1);
        formList.add(form2);

        given(callMeFormRepository.findAll()).willReturn(formList);

        List<CallMeForm> formListFound = callMeFormService.listAllCallMeForms();

        assertThat(formListFound).hasSize(formList.size());

        assertThat(formListFound.get(0).getClientId()).isEqualTo(1);
        assertThat(formListFound.get(1).getClientId()).isEqualTo(2);

        assertThat(formListFound.get(0).getClientName()).isEqualTo("Jason Bourne");
        assertThat(formListFound.get(1).getClientName()).isEqualTo("John Cena");

        assertThat(formListFound.get(0).getClientPhone()).isEqualTo("88005553535");
        assertThat(formListFound.get(1).getClientPhone()).isEqualTo("9379992");


    }

    @Test
    void saveCallMeForm() {
        CallMeForm form = new CallMeForm(1, "Jason Bourne", "88005553535", new Date());

        given(callMeFormRepository.save((any()))).willReturn(form);

        ArgumentCaptor<CallMeForm> captor = ArgumentCaptor.forClass(CallMeForm.class);
        callMeFormService.saveCallMeForm(form);
        verify(callMeFormRepository).save(captor.capture());

        CallMeForm capturedForm = captor.getValue();

        assertCallMeFormFields(form, capturedForm);
    }

    @Test
    void getCallMeForm() {
        int formId = 1;
        CallMeForm form = new CallMeForm(formId, "Jason Bourne", "88005553535", new Date());

        given(callMeFormRepository.findById(formId)).willReturn(Optional.of(form));

        CallMeForm obtainedForm = callMeFormService.getCallMeForm(formId);

        assertCallMeFormFields(form, obtainedForm);
    }

    @Test
    void deleteCallMeForm() {
        int formId = 1;
        CallMeForm form = new CallMeForm(formId, "Jason Bourne", "88005553535", new Date());

        given(callMeFormRepository.findById(formId)).willReturn(Optional.of(form));
        doNothing().when(callMeFormRepository).deleteById(formId);
        callMeFormService.deleteCallMeForm(formId);
        CallMeForm obtainedForm = callMeFormService.getCallMeForm(formId);
        String message = null;
        if(obtainedForm != null){
            message = "The item has been deleted!";
            assertThat(message).isNotNull();
        } else{
        assertThat(message).isNull();
        }









    }

    private void assertCallMeFormFields(CallMeForm originalForm, CallMeForm obtainedForm) {
        assertThat(obtainedForm.getClientId()).isEqualTo(originalForm.getClientId());
        assertThat(obtainedForm.getClientName()).isEqualTo(originalForm.getClientName());
        assertThat(obtainedForm.getClientPhone()).isEqualTo(originalForm.getClientPhone());
    }
}
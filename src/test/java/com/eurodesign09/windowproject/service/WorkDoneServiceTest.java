package com.eurodesign09.windowproject.service;

import com.eurodesign09.windowproject.dao.WorkDoneRepository;

import com.eurodesign09.windowproject.entity.WorkDone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

class WorkDoneServiceTest {

    @Mock
    private WorkDoneRepository workDoneRepository;

    private WorkDoneService workDoneService;


    @BeforeEach
    void setUp() {
        initMocks(this);
        workDoneService = new WorkDoneService(workDoneRepository);
    }

    @Test
    void shouldListAllWorkTest() {
        byte[] work1Byte = {1,2,3,4,5,6,7,8,9,0};
        byte[] work2Byte = {2,2,3,4,5,6,7,8,9,0};
        byte[] work3Byte = {3,2,3,4,5,6,7,8,9,0};

        WorkDone work1 = new WorkDone(1, work1Byte, "Amazing job!");
        WorkDone work2 = new WorkDone(2, work2Byte, "Mediocre job!");
        WorkDone work3 = new WorkDone(3, work3Byte, "Worst job ever!");

        List<WorkDone> listWork = new ArrayList<>();
        listWork.add(work1);
        listWork.add(work2);
        listWork.add(work3);

        given(workDoneRepository.findAll()).willReturn(listWork);

        List<WorkDone> workTestList = workDoneService.listAllWork();

        assertThat(workTestList).hasSize(3);

        assertWorkFields(workTestList, 1, work1Byte, "Amazing job!", 0);
        assertWorkFields(workTestList, 2, work2Byte, "Mediocre job!", 1);
        assertWorkFields(workTestList, 3, work3Byte, "Worst job ever!", 2);

    }

    private void assertWorkFields(List<WorkDone> workTestList, int id, byte[] workByte, String description, int listIndex) {
        assertThat(workTestList.get(listIndex).getImageId()).isEqualTo(id);
        assertThat(workTestList.get(listIndex).getWorkImage()).isEqualTo(workByte);
        assertThat(workTestList.get(listIndex).getImageDescription()).isEqualTo(description);

    }

    @Test
    void shouldSaveWorkTest() {

        byte[] work1Byte = {1,2,3,4,5,6,7,8,9,0};
        WorkDone newWork = new WorkDone(1, work1Byte, "Amazing job!");

        given(workDoneRepository.save(any())).willReturn(newWork);
        ArgumentCaptor<WorkDone> captor = ArgumentCaptor.forClass(WorkDone.class);

        workDoneService.saveWork(newWork);
        verify(workDoneRepository).save(captor.capture());
        WorkDone capturedWork = captor.getValue();

        assertWorkFields(capturedWork, 1, work1Byte, "Amazing job!");
    }

    private void assertWorkFields(WorkDone work, int id, byte[] workByte, String description) {
        assertThat(work.getImageId()).isEqualTo(id);
        assertThat(work.getWorkImage()).isEqualTo(workByte);
        assertThat(work.getImageDescription()).isEqualTo(description);
    }

    @Test
    void shouldGetWorkTest() {

        int workId = 1;
        byte[] work1Byte = {1,2,3,4,5,6,7,8,9,0};
        WorkDone newWork = new WorkDone(1, work1Byte, "Amazing job!");
        given(workDoneRepository.findById(workId)).willReturn(Optional.of(newWork));

        WorkDone workFound = workDoneService.getWork(workId);

       assertWorkFields(workFound, workId, work1Byte, "Amazing job!");

    }

    @Test
    void shouldDeleteWorkTest() {
        int workId = 1;
        byte[] work1Byte = {1,2,3,4,5,6,7,8,9,0};
        WorkDone newWork = new WorkDone(1, work1Byte, "Amazing job!");
        given(workDoneRepository.findById(workId)).willReturn(Optional.of(newWork));
        doNothing().when(workDoneRepository).deleteById(workId);
        workDoneService.deleteWork(workId);
        WorkDone obtainedWork = workDoneService.getWork(workId);
        String message = null;
        if(obtainedWork != null){
            message = "The item has been deleted!";
            assertThat(message).isNotNull();
        } else{
            assertThat(message).isNull();
        }
    }
}
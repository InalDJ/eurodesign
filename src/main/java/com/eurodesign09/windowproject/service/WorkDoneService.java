package com.eurodesign09.windowproject.service;

import com.eurodesign09.windowproject.dao.WorkDoneRepository;
import com.eurodesign09.windowproject.entity.WorkDone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkDoneService {


    private WorkDoneRepository workDoneRepository;

    @Autowired
    public WorkDoneService(WorkDoneRepository workDoneRepository) {
        this.workDoneRepository = workDoneRepository;
    }

    public List<WorkDone> listAllWork() {
        return workDoneRepository.findAll();
    }

    public void saveWork (WorkDone workDone) {
        workDoneRepository.save(workDone);
    }

    public WorkDone getWork (Integer id) {
        return workDoneRepository.findById(id).get();
    }

    public void deleteWork (Integer id) {
        workDoneRepository.deleteById(id);
    }
}

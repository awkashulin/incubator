package com.reserv.incubator.service;

import com.reserv.incubator.db.IncubationData;
import com.reserv.incubator.repository.IncubationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class IncubationDatabaseService {

    @Autowired
    private IncubationRepository incubationRepository;

    @PostConstruct
    private void setUp() {

    }

    public IncubationData saveIncubationData(IncubationData incubationData) {

        return incubationRepository.save(incubationData);
    }

    public IncubationData getIncubationDataById(long id) {
        return incubationRepository.findById(id).get();
    }

}

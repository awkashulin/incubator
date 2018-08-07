package com.reserv.incubator.loader;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Log4j2
@Service
public class ScheduledTasks {

    @Autowired
    RestDataProcessor restDataProcessor;

    @Scheduled(cron = "*/1 * * * * *")
    public void loadData() {
        log.info("Load data from rest...");
        restDataProcessor.processData();
    }
}

package com.reserv.incubator.loader;

import com.reserv.incubator.db.IncubationData;
import com.reserv.incubator.service.IncubationDataTranslator;
import com.reserv.incubator.service.IncubationDatabaseService;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Log4j2
@Service
public class RestDataProcessor {
    private final Integer COEFF = 1;
    @Autowired
    private IncubatorRestClient incubatorRestClient;

    @Autowired
    private IncubationDatabaseService incubationDatabaseService;

    @Value("${pointsToSave}")
    private String[] pointsToSave;

    public void processData() {
        try {
            JSONObject result = incubatorRestClient.loadData();
            if (result.get("query_type").equals("QueryCPValues")) {
                Map<String, Object> data = translateData((JSONArray) result.get("cp_values"));
                Integer dayNumber = getDayNumber(data);
                saveData(data, dayNumber);
            }
        } catch(Exception e) {
            log.error("Error process data:", e);
        }
    }

    private Integer getDayNumber(Map<String, Object> data) {
        Double dayStart = (Double)data.get("cpsDateTimeStartIncubation");
        Double dayCurrent = (Double)data.get("cpsDateTimeNow");

        return (dayStart.intValue() - dayCurrent.intValue()) / COEFF;
    }

    private Map<String,Object> translateData(JSONArray data) {
        IncubationDataTranslator trans = new IncubationDataTranslator(pointsToSave);
        return trans.translate(data);
    }

    //save data to DB
    private void saveData(Map<String, Object> data, Integer dayNumber) {

        try {
            for (String point : pointsToSave) {
                IncubationData incubation = new IncubationData();
                incubation.setDayNumber(dayNumber);
                incubation.setDate(new Date());
                incubation.setPoint(point);
                incubation.setValue(data.get(point).toString());

                incubationDatabaseService.saveIncubationData(incubation);
            }
        } catch(Exception e) {
            log.error("Error saving data:", e);
        }

    }

}

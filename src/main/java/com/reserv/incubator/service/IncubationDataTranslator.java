package com.reserv.incubator.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

public class IncubationDataTranslator {

    private String[] pointsToSave;

    public IncubationDataTranslator(String[] pointsToSave) {
        this.pointsToSave = pointsToSave;
    }

    public Map<String, Object> translate(JSONArray data) {

        Map<String, Object> result = new HashMap<>();

        for(int i = 0; i < data.size(); i++) {
            JSONArray item =  (JSONArray)data.get(i);
            result.put(item.get(0).toString(), item.get(1));
        }


        return result;
    }
}

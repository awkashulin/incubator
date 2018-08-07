package com.reserv.incubator.loader;

import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Service
public class IncubatorRestClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${data.url}")
    String dataUrl;

    public JSONObject loadData() {

        JSONObject jsonObject;

        try {
            log.info("Load incubation data from:" + dataUrl);
            String result = restTemplate.getForObject(dataUrl, String.class);

            JSONParser parser = new JSONParser();
            jsonObject = (JSONObject) parser.parse(result);
        } catch (ParseException e) {
            e.printStackTrace();
            log.error("Error loading json:", e);
            return null;
        }

        return jsonObject;
    }

}

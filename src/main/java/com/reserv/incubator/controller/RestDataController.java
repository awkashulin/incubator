package com.reserv.incubator.controller;

import com.reserv.incubator.db.IncubationData;
import com.reserv.incubator.model.Ranges;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
public class RestDataController {

    @GetMapping(value="/getData/{point}/{range}", produces = "application/json")
    @ResponseBody
    public List<IncubationData> getIncubationData(@PathVariable String point, @PathVariable String range)
            throws IllegalAccessException
    {
        try {
            List<IncubationData> result = new ArrayList<>();
            Ranges r = Ranges.getByName(range);

            return result;
        } catch (Exception e) {
            log.error("Error in getIncubationData:", e);
            throw new IllegalAccessException(e.getMessage());
        }
    }
}

package com.robihidayat.shopie.promo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author robihidayat
 */


@Slf4j
@Controller
public class Index {

    @RequestMapping(value = "/getDateAndTime")
    public ModelAndView getDateAndTime() {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String date_time = dtf.format(now);

        Map<String, Object> params = new HashMap<>();
        params.put("date_time", date_time);

        return new ModelAndView("showMessage", params);
    }
}

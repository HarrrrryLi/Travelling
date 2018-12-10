package com.fengru.Travelling.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController("/hotel_single")
public class HotelSingleController {
    @RequestMapping(value="/hotel_single")
    public ModelAndView hotel_single(){
        return new ModelAndView("hotel-single");
    }
}

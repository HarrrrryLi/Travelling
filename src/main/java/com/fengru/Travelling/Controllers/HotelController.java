package com.fengru.Travelling.Controllers;

import com.fengru.Travelling.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController("/hotel")
public class HotelController {
    @Autowired
    DBRepository dbRepository;

    @RequestMapping(value= "/hotel", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView hotel() {
        return new ModelAndView("hotel");
    }
}

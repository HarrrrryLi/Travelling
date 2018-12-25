package com.Travelling.Controllers;

import com.Travelling.Repositories.Entities.Place;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController("/hotel_single")
public class HotelSingleController {
    @RequestMapping(value="/hotel_single")
    public ModelAndView hotel_single(@ModelAttribute Place place){
        ModelAndView view = new ModelAndView("hotel-single");
        String name = place.getName();
        view.addObject("name", name);
        return view;
    }
}

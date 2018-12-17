package com.Travelling.Controllers;

import com.Travelling.DBRepository;
import com.Travelling.Place;
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
        ModelAndView view = new ModelAndView("hotel");
        view.addObject("place", new Place(0, "Holiday Inn", "848-111-1111", "www...", "String address", "String city", "String state", "String country", "String zip", 0d, 0d));
        return view;
    }
}

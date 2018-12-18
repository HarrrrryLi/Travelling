package com.Travelling.Controllers;

import com.Travelling.DBRepository;
import com.Travelling.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController("/hotel")
public class HotelController {
    @Autowired
    DBRepository dbRepository;

    @RequestMapping(value= "/hotel", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView hotel() {
        ModelAndView view = new ModelAndView("hotel");
        List<Place> hotelList = new ArrayList<>();
        hotelList.add(new Place(0, "Holiday Inn1", "tel", "web", "address", "city", "state", "country", "zip", 0d, 0d));
        hotelList.add(new Place(1, "Holiday Inn2", "tel", "web", "address", "city", "state", "country", "zip", 0d, 0d));
        hotelList.add(new Place(2, "Holiday Inn3", "tel", "web", "address", "city", "state", "country", "zip", 0d, 0d));
        hotelList.add(new Place(3, "Holiday Inn4", "tel", "web", "address", "city", "state", "country", "zip", 0d, 0d));
        hotelList.add(new Place(4, "Holiday Inn5", "tel", "web", "address", "city", "state", "country", "zip", 0d, 0d));
        hotelList.add(new Place(5, "Holiday Inn6", "tel", "web", "address", "city", "state", "country", "zip", 0d, 0d));
        view.addObject("hotel_list", hotelList);
        return view;
    }
}

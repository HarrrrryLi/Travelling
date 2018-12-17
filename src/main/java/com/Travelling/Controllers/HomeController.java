package com.Travelling.Controllers;

import com.Travelling.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController("/")
public class HomeController {

    @Autowired
    DBRepository dbRepository;

    @GetMapping("/")
    public ModelAndView Homepage() {
        ModelAndView view = new ModelAndView("index");
        view.addObject("search", new Search());
        /**** This part will be done with database****/
        List<DisplayPlace> destination_list = new ArrayList<>();
        destination_list.add(new Destination("Piscataway", "USA",5.0f,30,"images/destination-1.jpg"));
        destination_list.add(new Destination("Piscataway", "USA",4.5f,25,"images/destination-2.jpg"));
        destination_list.add(new Destination("Piscataway", "USA",4.0f,20,"images/destination-3.jpg"));
        destination_list.add(new Destination("Piscataway", "USA",3.5f,15,"images/destination-4.jpg"));
        destination_list.add(new Destination("Piscataway","USA",3.0f,10,"images/destination-5.jpg"));

        List<DisplayPlace> hotel_list = new ArrayList<>();
        hotel_list.add(new ServicePlace("Hotel1","Piscataway","USA",200,5.0f,30, "images/hotel-1.jpg"));
        hotel_list.add(new ServicePlace("Hotel2","Piscataway","USA",150,4.5f,25,"images/hotel-2.jpg"));
        hotel_list.add(new ServicePlace("Hotel3","Piscataway","USA",100,4.0f,20,"images/hotel-3.jpg"));
        hotel_list.add(new ServicePlace("Hotel4","Piscataway","USA",50,3.5f,15,"images/hotel-4.jpg"));

        List<DisplayPlace> restaurant_list = new ArrayList<>();
        restaurant_list.add(new ServicePlace("Restaurant1","Edison","USA",200,5.0f,30,"images/restaurant-1.jpg"));
        restaurant_list.add(new ServicePlace("Restaurant2","Edison","USA",150,4.5f,25,"images/restaurant-2.jpg"));
        restaurant_list.add(new ServicePlace("Restaurant3","Edison","USA",100,4.0f,20,"images/restaurant-3.jpg"));
        restaurant_list.add(new ServicePlace("Restaurant4","Edison","USA",50,3.5f,15,"images/restaurant-4.jpg"));

        /**Database Part END HERE***/
        view.addObject("destination_list", destination_list);
        view.addObject("hotel_list",hotel_list);
        view.addObject("restaurant_list",restaurant_list);


        return view;
    }

}

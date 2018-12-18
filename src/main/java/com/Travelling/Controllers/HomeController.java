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
        List<Destination> destination_list = new ArrayList<>();
        destination_list.add(new Destination("Piscataway","NJ", "USA",5.0f,30,"images/destination-1.jpg"));
        destination_list.add(new Destination("Edison","NJ", "USA",4.5f,25,"images/destination-2.jpg"));
        destination_list.add(new Destination("Newark","NJ", "USA",4.0f,20,"images/destination-3.jpg"));
        destination_list.add(new Destination("Princeton","NJ", "USA",3.5f,15,"images/destination-4.jpg"));
        destination_list.add(new Destination("New Brunswick","NJ","USA",3.0f,10,"images/destination-5.jpg"));

        List<ServicePlace> hotel_list = new ArrayList<>();
        hotel_list.add(new ServicePlace(1,"Hotel1","Piscataway","NJ","USA",200,5.0f,30, "images/hotel-1.jpg"));
        hotel_list.add(new ServicePlace(2,"Hotel2","Edison","NJ","USA",150,4.5f,25,"images/hotel-2.jpg"));
        hotel_list.add(new ServicePlace(3,"Hotel3","Newark","NJ","USA",100,4.0f,20,"images/hotel-3.jpg"));
        hotel_list.add(new ServicePlace(4,"Hotel4","Princeton","NJ","USA",50,3.5f,15,"images/hotel-4.jpg"));
        hotel_list.add(new ServicePlace(5,"Hotel5","New Brunswick","NJ","USA",25,3.0f,10,"images/hotel-2.jpg"));

        List<ServicePlace> restaurant_list = new ArrayList<>();
        restaurant_list.add(new ServicePlace(6,"Restaurant1","Piscataway","NJ","USA",200,5.0f,30,"images/restaurant-1.jpg"));
        restaurant_list.add(new ServicePlace(7,"Restaurant2","Edison","NJ","USA",150,4.5f,25,"images/restaurant-2.jpg"));
        restaurant_list.add(new ServicePlace(8,"Restaurant3","Newark","NJ","USA",100,4.0f,20,"images/restaurant-3.jpg"));
        restaurant_list.add(new ServicePlace(9,"Restaurant4","Princeton","NJ","USA",50,3.5f,15,"images/restaurant-4.jpg"));
        restaurant_list.add(new ServicePlace(10,"Restaurant5","New Brunswick","NJ","USA",25,3.0f,10,"images/restaurant-2.jpg"));

        /**Database Part END HERE***/
        view.addObject("destination_list", destination_list);
        view.addObject("hotel_list",hotel_list);
        view.addObject("restaurant_list",restaurant_list);


        return view;
    }

}

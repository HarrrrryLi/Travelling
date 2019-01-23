package com.Travelling.Controllers;

import com.Travelling.*;
import com.Travelling.Repositories.Entities.Place;
import com.Travelling.Repositories.DBRepository;
import com.Travelling.Repositories.Entities.Tag;
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

        /**Database Part END HERE***/

        List<Tag> type_list = dbRepository.FindAllTags();
        List<Place> hotel_list = dbRepository.getPlacefromTag("lodging");
        hotel_list.sort(new PlaceRateComparator());
        int size = hotel_list.size();
        hotel_list = hotel_list.subList(size - 5, size);

        List<Place> restaurant_list = dbRepository.getPlacefromTag("restaurant");
        restaurant_list.sort(new PlaceRateComparator());
        size = restaurant_list.size();
        restaurant_list = restaurant_list.subList(size - 5, size);


        type_list.add(new Tag(0, "Please Select A Type"));
        view.addObject("type_list",type_list);
        view.addObject("destination_list", destination_list);
        view.addObject("hotel_list",hotel_list);
        view.addObject("restaurant_list",restaurant_list);

        view.addObject("user_nums", dbRepository.CountUsers());
        view.addObject("hotel_nums",dbRepository.CountPlaces("lodging"));
        view.addObject("restaurant_nums", dbRepository.CountPlaces("restaurant"));
        view.addObject("destination_nums", dbRepository.CountDestinations());


        return view;
    }

}

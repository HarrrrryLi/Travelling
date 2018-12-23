package com.Travelling.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Travelling.*;
import com.Travelling.GooglePlace.GooglePlace;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Controller {
	@Autowired
	DBRepository dbRepository;

	@RequestMapping(value= "/home")
	public ModelAndView home() {
		return new ModelAndView("redirect:/");		
	}

	@RequestMapping(value= "/contact", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView contact() {
		return new ModelAndView("contact");
	}
	
	@RequestMapping(value= "/about", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView about() {
		return new ModelAndView("about");
	}

	@RequestMapping("/test")
	public ModelAndView testpage(@ModelAttribute Search search) {
        ModelAndView view = new ModelAndView("test");
        String keyword = search.getKeyword();
        String location = search.getLocation();
        List<Place> result;
        if(location.isEmpty() && keyword.isEmpty())
            return new ModelAndView("redirect:/tour");
        else if(location.isEmpty())
            result = dbRepository.getPlacefromTag(keyword);
        else if(keyword.isEmpty())
            result = dbRepository.getPlacefromLocation(location);
        else
            result = dbRepository.getPlacefromTagAndLocation(keyword, location);
        view.addObject("size",result.size());
        view.addObject("longitude",result.get(0).getLongitude());
        view.addObject("latitude",result.get(0).getLatitude());
        return view;
	}

	@RequestMapping(value = "/test1")
    public ModelAndView test1(@ModelAttribute("destination") Destination destination, @ModelAttribute("place") Place place ){
	    ModelAndView view = new ModelAndView();
	    String city_str;
	    if(destination != null && destination.getCity() != null)
	    	city_str = destination.getCity();
	    else
	    	city_str = place.getCity();
	    view.addObject("city", city_str);
	    return view;
    }

    @RequestMapping(value = "/update")
	public String update(){
        String api_key = "AIzaSyDuJNzA_7XO2m9DZcH16B9k4PRFUod3-ds";
        String URL = String.format("https://maps.googleapis.com/maps/api/place/textsearch/json?query=%s&type=%s&location=%f, %f&radius=50000&key=%s","hotel","hotel",34.031134, -118.289338, api_key);
        RestTemplate restTemplate = new RestTemplate();
        String json= restTemplate.getForObject(URL, String.class);
        return json;
	}
}

package com.Travelling.Controllers;

import java.util.ArrayList;
import java.util.List;

import com.Travelling.*;
import com.Travelling.Repositories.Entities.Place;
import com.Travelling.Repositories.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.ws.http.HTTPException;

@RestController
public class Controller {
	@Autowired
	private DBRepository dbRepository;
	private  List<String> query_list = new ArrayList<>();

	public Controller(){
		query_list.add("restaurant");
		query_list.add("lodging");
		query_list.add("amusement park");
		query_list.add("art gallery");
		query_list.add("aquarium");
		query_list.add("casino");
		query_list.add("stadium");
		query_list.add("shopping_mall");
		query_list.add("park");
		query_list.add("zoo");

	}

	@RequestMapping(value = "/home")
	public ModelAndView home() {
		return new ModelAndView("redirect:/");		
	}

//	@RequestMapping
//	@ResponseStatus(code = HttpStatus.NOT_FOUND)
//	public ModelAndView defaultPath() {
//		return new ModelAndView("redirect:/");
//	}

	@RequestMapping(value = "/contact", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView contact() {
		return new ModelAndView("contact");
	}
	
	@RequestMapping(value = "/about", method = {RequestMethod.GET, RequestMethod.POST})
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
		dbRepository.UpdateAdminUser();
		boolean error_occurs = dbRepository.UpdateDatabaseAll(query_list,40.523323, -74.458255);

		if(error_occurs)
			return "Error Occurs";
		else
			return "Updated";
	}


}

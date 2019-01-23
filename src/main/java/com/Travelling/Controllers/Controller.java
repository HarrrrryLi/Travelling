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
        int tid = search.getType();
        String location = search.getLocation();
        List<Place> result;
        if(location.isEmpty() && tid == 0)
            return new ModelAndView("redirect:/tour");
        else if(location.isEmpty())
            result = dbRepository.getPlacefromTid(tid);
        else if(tid == 0)
            result = dbRepository.getPlacefromLocation(location);
        else
            result = dbRepository.getPlacefromTagAndLocation(tid, location);
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

}

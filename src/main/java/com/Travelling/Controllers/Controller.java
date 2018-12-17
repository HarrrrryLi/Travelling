package com.Travelling.Controllers;

import java.util.List;

import com.Travelling.DBRepository;
import com.Travelling.Search;
import com.Travelling.Destination;
import com.Travelling.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
    public ModelAndView test1(@ModelAttribute("destination") Destination destination){
	    ModelAndView view = new ModelAndView();
	    view.addObject("city",destination.getCity());
	    return view;
    }

}

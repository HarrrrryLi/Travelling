package com.fengru.Travelling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class Controller {
	
	@Autowired
	DBRepository dbRepository;
	
	@GetMapping("/")
	public ModelAndView Homepage() {
		ModelAndView view = new ModelAndView("index");
		view.addObject("search", new Search());
		/**** This part will be done with database****/
		List<TourPackage> result = new ArrayList<>();
		result.add(new TourPackage("Piscataway", "USA",5.0f,200,30));
		result.add(new TourPackage("Piscataway", "USA",4.5f,200,25));
		result.add(new TourPackage("Piscataway", "USA",4.0f,200,20));
		result.add(new TourPackage("Piscataway", "USA",3.5f,200,15));
		/**Database Part END HERE***/
//		Map<String, TourPackage> TourPackage_list = new HashMap<>();
//		for(int cnt = 0 ; cnt < result.size(); cnt++)
//			TourPackage_list.put("TOP"+(cnt+1),result.get(cnt));
//		view.addAllObjects(TourPackage_list);

		view.addObject("tourpackage_list",result);
		view.addObject("destination_list", result);


		return view;
	}

	@RequestMapping(value= "/home")
	public ModelAndView home() {
		return new ModelAndView("redirect:/");		
	}
	
	@RequestMapping(value= "/login", method= {RequestMethod.GET, RequestMethod.POST})
	public String Login() {
		return "Login Page";
	}
	
	@RequestMapping(value= "/signup", method= {RequestMethod.GET, RequestMethod.POST})
	public String Signup() {
		return "Signup";
	}
	
	@RequestMapping(value= "/contact", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView contact() {
		return new ModelAndView("contact");
	}
	
	@RequestMapping(value= "/about", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView about() {
		return new ModelAndView("about");
	}

	@RequestMapping(value= "/hotel", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView hotel() {
		return new ModelAndView("hotel");
	}
	
	@RequestMapping(value="/hotel_single")
	public ModelAndView hotel_singel(){
		return new ModelAndView("hotel-single");
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
    public ModelAndView test1(@ModelAttribute("destination") TourPackage destination){
	    ModelAndView view = new ModelAndView();
	    view.addObject("city",destination.getCity());
	    return view;

    }

}

package com.fengru.Travelling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
		return view;
	}
	
	@PostMapping("/")
	public ModelAndView Search(@ModelAttribute Search search, RedirectAttributes redirectAttributes) {
		String keyword = search.getKeyword();
		List<Place> result = dbRepository.getPlacefromTag(keyword);
		redirectAttributes.addAttribute("result", result.get(0));
		return new ModelAndView("redirect:/test");
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
	
	@RequestMapping(value= "/blog", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView blog() {
		return new ModelAndView("blog");
	}
	
	@RequestMapping(value= "/hotel", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView hotel() {
		return new ModelAndView("hotel");
	}
	
	@RequestMapping(value="/hotel_single")
	public ModelAndView hotel_singel(){
		return new ModelAndView("hotel-single");
	}
	
	@RequestMapping(value= "/tour", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView tour() {
		return new ModelAndView("tour");
	}
	
	@RequestMapping(value= "/test", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView test(@ModelAttribute("result") Place result) {
		ModelAndView view = new ModelAndView("test");
		view.addObject("address", result.getAddress());
		return view;
	}

}

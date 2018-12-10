package com.Travelling.Controllers;

import com.Travelling.DBRepository;
import com.Travelling.Search;
import com.Travelling.TourPackage;
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

}

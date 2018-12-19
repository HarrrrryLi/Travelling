package com.Travelling.Controllers;

import com.Travelling.DBRepository;
import com.Travelling.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController("/hotel")
public class HotelController {
    @Autowired
    DBRepository dbRepository;

    @RequestMapping(value= "/hotel", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView hotel(@RequestParam(value = "pageNum", defaultValue = "1") String pageNum) {
        ModelAndView view = new ModelAndView("hotel");
        /*this list is for demo only, will be initialized by db later*/
        List<Place> hotelList = new ArrayList<>();
        hotelList.add(new Place(0, "Holiday Inn1", "tel", "web", "address", "city", "state", "country", "zip", 0d, 0d));
        hotelList.add(new Place(1, "Holiday Inn2", "tel", "web", "address", "city", "state", "country", "zip", 0d, 0d));
        hotelList.add(new Place(2, "Holiday Inn3", "tel", "web", "address", "city", "state", "country", "zip", 0d, 0d));
        hotelList.add(new Place(3, "Holiday Inn4", "tel", "web", "address", "city", "state", "country", "zip", 0d, 0d));
        hotelList.add(new Place(4, "Holiday Inn5", "tel", "web", "address", "city", "state", "country", "zip", 0d, 0d));
        hotelList.add(new Place(5, "Holiday Inn6", "tel", "web", "address", "city", "state", "country", "zip", 0d, 0d));
        hotelList.add(new Place(6, "Holiday Inn6", "tel", "web", "address", "city", "state", "country", "zip", 0d, 0d));
        hotelList.add(new Place(7, "Holiday Inn6", "tel", "web", "address", "city", "state", "country", "zip", 0d, 0d));
        hotelList.add(new Place(8, "Holiday Inn6", "tel", "web", "address", "city", "state", "country", "zip", 0d, 0d));
        hotelList.add(new Place(9, "Holiday Inn6", "tel", "web", "address", "city", "state", "country", "zip", 0d, 0d));

        int len = hotelList.size();
        int totalPage = len % 6 == 0 ? len / 6 : len / 6 + 1;
        int totalRows = totalPage % 5 == 0 ? totalPage / 5 : totalPage / 5 + 1;
        int num = Integer.parseInt(pageNum) - 1;
        int startIdx = num * 6;
        int endIdx = startIdx + 6 >len ? len : (startIdx + 6);
        int startRow = (num / 5) * 5 + 1;
        int endRow = startRow + 5 < totalPage ? startRow + 5 : totalPage;
        hotelList = hotelList.subList(startIdx, endIdx);

        view.addObject("hotel_list", hotelList);
        view.addObject("total_page", totalPage);
        view.addObject("total_rows", totalRows);
        view.addObject("cur_page", num + 1);
        view.addObject("start_row", startIdx);
        view.addObject("end_row", endRow);
        return view;
    }
}

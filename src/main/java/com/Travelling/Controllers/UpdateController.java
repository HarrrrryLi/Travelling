package com.Travelling.Controllers;

import com.Travelling.Repositories.DBRepository;
import com.Travelling.UpdateList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UpdateController {
    @Autowired
    private DBRepository dbRepository;
    private List<String> query_list = new ArrayList<>();

    public UpdateController(){
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

    @GetMapping(value = "/update")
    public ModelAndView GetUpdate(){
        ModelAndView view = new ModelAndView("update");
        view.addObject("type_list", query_list);
        view.addObject("update_list", new UpdateList());
        return view;
    }


    @GetMapping(value = "/updated")
    public String GetUpdated(){
        return "redirect:/update";
    }

    @PostMapping(value = "/updated")
    public String PostUpdated(@ModelAttribute("update_list") UpdateList updateList){
        if(updateList == null)
            return "None of Types SELECT";

        dbRepository.UpdateAdminUser();
        boolean error_occurs = dbRepository.UpdateDatabaseAll(updateList.getTypes(),40.523323, -74.458255);

        if(error_occurs)
            return "Error Occurs";
        else
            return "Updated";
    }
}

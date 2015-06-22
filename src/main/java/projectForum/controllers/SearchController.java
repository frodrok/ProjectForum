package projectForum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import projectForum.model.Search;

import javax.validation.Valid;

/**
 * Created by User on 2015-06-22.
 */

@Controller
public class SearchController {

    @RequestMapping(value = "/search", method =RequestMethod.GET)
    public String searchGET(@ModelAttribute("search") Search search, ModelMap map) {
        map.put("display", "search");
        return "root";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchPOST(@Valid @ModelAttribute("search") Search search, BindingResult result,
                             ModelMap map) {

        // if (result.hasErrors()) {
            map.put("display", "search");
            return "root";
        // lse {

        //



    }

}

package projectForum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import projectForum.model.Rating;

import javax.validation.Valid;

/**
 * Created by User on 2015-06-22.
 */

@Controller
public class RatingController {

    @RequestMapping(value = "/rate", method = RequestMethod.POST)
    public String ratePost(@Valid @ModelAttribute("rating") Rating rating, BindingResult result,
                           ModelMap map) {

        System.out.println(rating.getRating() + " - " + rating.getTopic_id());


        return "redirect:/";

    }
}

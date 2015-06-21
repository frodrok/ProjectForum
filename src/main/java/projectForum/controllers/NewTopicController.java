package projectForum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import projectForum.model.Topic;
import projectForum.repository.TopicRepository;

import javax.validation.Valid;

/**
 * Created by frodrok on 2015-06-21.
 */

@Controller
public class NewTopicController {

    @Autowired
    private TopicRepository topicRepository;

    @RequestMapping(value = "/newtopic", method = RequestMethod.GET)
    public String newTopicGet(@ModelAttribute("topic") Topic topic, ModelMap map) {
        map.put("display", "newtopic");
        return "root";
    }

    @RequestMapping(value = "/newtopic", method = RequestMethod.POST)
    public String newTopicPost(@Valid @ModelAttribute("topic") Topic topic, BindingResult result,
                               ModelMap map) {

        if (result.hasErrors()) {

            map.put("display", "newtopic");
            return "root";

        } else {

            topic.setCreatedDate(System.currentTimeMillis());
            topic.setUpdatedDate(System.currentTimeMillis());

            topicRepository.insert(topic);

            /* PRG */
            return "redirect:/";
        }



    }
}

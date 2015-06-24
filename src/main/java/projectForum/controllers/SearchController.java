package projectForum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import projectForum.model.Search;
import projectForum.model.Topic;
import projectForum.repository.MessageRepository;
import projectForum.repository.TopicRepository;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by User on 2015-06-22.
 */

@Controller
public class SearchController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping(value = "/search", method =RequestMethod.GET)
    public String searchGET(@ModelAttribute("search") Search search, ModelMap map) {
        map.put("display", "search");
        return "root";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchPOST(@Valid @ModelAttribute("search") Search search, BindingResult result,
                             ModelMap map) {

        if (result.hasErrors()) {

            map.put("display", "search");
            return "root";

        } else {

            List<Topic> foundTopics = topicRepository.search(search.getSearchString());

            if (foundTopics.size() < 1) {

                map.put("nofoundtopics", true);

            } else {

                for (Topic topic : foundTopics) {
                    System.out.println(topic.getTitle());
                }

                map.put("foundtopics", foundTopics);
            }


            map.put("display", "search");
            return "root";
        }





    }

}

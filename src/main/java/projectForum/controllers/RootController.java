package projectForum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import projectForum.repository.TopicRepository;
import projectForum.repository.UserRepository;

@Controller
public class RootController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String rootGET(ModelMap map) {

        map.put("user", userRepository.findById(1));
        map.put("topics", topicRepository.findTopics());
        map.put("display", "root");

        return "root";
    }
}

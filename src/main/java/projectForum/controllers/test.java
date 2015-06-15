package projectForum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import projectForum.model.Message;
import projectForum.model.Topic;
import projectForum.model.User;
import projectForum.repository.TopicRepository;
import projectForum.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2015-06-15.
 */
@Controller
public class test {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TopicRepository topicRepository;

    @RequestMapping("/")
    String base(ModelMap map) {
        List<String> test = new ArrayList<>();
        test.add("testerino");
        test.add("another testerino");
        test.add("a third testerino");
        map.put("allTopics", test);

        // userRepository.save(new User("froderino", "testerino"));

        map.put("users", userRepository.findAll());
        map.put("topics", topicRepository.findAll());

        return "offbase";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    String offbase(@ModelAttribute("topic") Topic topic) {

        topicRepository.save(topic);

        return "redirect:/";
    }
}


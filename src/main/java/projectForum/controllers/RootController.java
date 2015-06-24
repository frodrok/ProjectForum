package projectForum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import projectForum.model.Rating;
import projectForum.model.Topic;
import projectForum.repository.MessageRepository;
import projectForum.repository.TopicRepository;
import projectForum.repository.UserRepository;

@Controller
public class RootController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String rootGetRedirect() {
        return "redirect:/newest";
    }

    @RequestMapping(value = "/{sorting}", method = RequestMethod.GET)
    public String rootGET(@PathVariable("sorting") String sorting, ModelMap map,
                          @ModelAttribute("rating") Rating rating) {

        if (sorting == null) {
            return "redirect:/newest";
        }

        Page<Topic> page = topicRepository.findTopicsWithPages();

        map.put("messagesAmount", messageRepository.getMessageCount());
        map.put("topicsAmount", topicRepository.getTopicCount());
        map.put("topics", topicRepository.findTopics(sorting));
        map.put("display", "root");

        return "root";
    }
}

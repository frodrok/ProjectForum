package projectForum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import projectForum.model.Message;
import projectForum.model.Topic;
import projectForum.repository.MessageRepository;
import projectForum.repository.TopicRepository;
import projectForum.repository.UserRepository;

import javax.validation.Valid;

/**
 * Created by frodrok on 2015-06-21.
 */

@Controller
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    @RequestMapping(value = "/topic/{id}/newmessage", method = RequestMethod.GET)
    public String newMessageGet(@PathVariable("id") int id, @ModelAttribute("message") Message message, ModelMap map) {

        Topic topic = topicRepository.findById(id);

        map.put("topic", topic);
        map.put("display", "newmessage");
        return "root";
    }

    @RequestMapping(value = "/topic/{id}/newmessage", method = RequestMethod.POST)
    public String newMessagePost(@Valid @ModelAttribute("message") Message message, BindingResult result,
                                 @PathVariable("id") int id,
                                 ModelMap map) {

        if (result.hasErrors()) {

            map.put("display", "newmessage");
            return "root";

        } else {

            message.setCreated_date(System.currentTimeMillis());
            message.setTopic_id(id);
            message.setUser(userRepository.findById(1));

            Topic topic = topicRepository.findById(id);
            topic.setUpdatedDate(System.currentTimeMillis());

            messageRepository.insert(message);
            topicRepository.update(topic);

            return "redirect:/topic/" + id;
        }

    }
}

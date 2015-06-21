package projectForum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import projectForum.model.Message;
import projectForum.model.Topic;
import projectForum.repository.MessageRepository;
import projectForum.repository.TopicRepository;

import java.util.List;

/**
 * Created by frodrok on 2015-06-21.
 */

@Controller
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping(value = "/topic/{id}", method = RequestMethod.GET)
    public String topicGet(@PathVariable("id") int id, ModelMap map) {

        Topic topic = topicRepository.findById(id);
        List<Message> messages;

        if (topic == null) {
            map.put("display", "notopic");
        } else {
            messages = messageRepository.findAllById(topic.getId());
            map.put("messages", messages);
            map.put("display", "topic");
            map.put("topic", topic);

        }

        return "root";
    }

}

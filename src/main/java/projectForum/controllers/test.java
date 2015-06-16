package projectForum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import projectForum.model.Forum;
import projectForum.model.Message;
import projectForum.model.Topic;
import projectForum.model.User;
import projectForum.repository.ForumRepository;
import projectForum.repository.MessageRepository;
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

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ForumRepository forumRepository;

    @RequestMapping("/")
    String base(@ModelAttribute("topic") Topic topic, ModelMap map) {

        List<Topic> topics = topicRepository.findAll();
        map.put("topics", topics);
        map.put("display", "bas");

        return "offbase";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    String offbase(@ModelAttribute("topic") Topic topic) {

        Forum forum = forumRepository.findOne(new Long(1));
        topic.setForum(forum);
        topic.setDate(System.currentTimeMillis());
        topicRepository.save(topic);

        return "redirect:/";
    }

    @RequestMapping(value = "/topic/{id}", method = RequestMethod.GET)
    String goToTopic(@PathVariable("id") int id, ModelMap map,
                     @ModelAttribute("message") Message message) {

        Long longId = new Long(id);
        Topic topic = topicRepository.findOne(longId);

        // you can iterate over topic.messages in thymeleaf template file since you
        // pass the whole topic in as "topic"
        map.put("messages", topic.getMessages());
        map.put("display", "topic");
        map.put("topic", topic);

        for (Message m : topic.getMessages()) {
            System.out.println(m.getId() + "] " + m.getMessageContent());
        }

        return "offbase";
    }

    @RequestMapping(value = "/topic/{id}", method = RequestMethod.POST)
    String postMessageToTopic(@PathVariable("id") int id, ModelMap map,
                              @ModelAttribute("message") Message message) {

        Long longId = new Long(id);
        Topic topic = topicRepository.findOne(longId);

        // has to make a new Message object to save it to the database
        Message newMessage = new Message();
        newMessage.setMessageContent(message.getMessageContent());

        Long idlong = new Long(3);
        User user = userRepository.findOne(idlong);
        newMessage.setUser(user);
        newMessage.setTopic(topic);

        messageRepository.save(newMessage);

        return "redirect:/topic/" + id;
    }

    @RequestMapping("/doallkinds")
    String execute() {

        // delete 6 first topics
        for (int i = 1; i <= 6; i++) {
            topicRepository.delete(new Long(i));
        }

        return "blank";
    }
}


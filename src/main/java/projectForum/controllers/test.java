package projectForum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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

        Forum forum = forumRepository.findOne(new Long(1));

        /* count total amount of messages in the whole forum */
        int messageCount = 0;
        for (Topic t : forum.getTopicList()) {
            for (Message m : t.getMessages()) {
                messageCount++;
            }
        }

        map.put("messageAmount", messageCount);
        map.put("topicsAmount", forum.getTopicList().size());
        map.put("topics", forum.getTopicList());
        map.put("display", "bas");

        return "offbase";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    String postNewTopic(@ModelAttribute("topic") Topic topic) {

        Forum forum = forumRepository.findOne(new Long(1));
        topic.setForum(forum);
        topic.setCreatedDate(System.currentTimeMillis());
        topicRepository.save(topic);
 
        return "redirect:/";
    }

    @RequestMapping(value = "/topic/{id}", method = RequestMethod.GET)
    String goToTopic(@PathVariable("id") int id, ModelMap map,
                     @ModelAttribute("message") Message message) {

        Long longId = new Long(id);
        Topic topic = topicRepository.findOne(longId);

        if (topic != null) {
            if (topic.getMessages() != null) {
                map.put("messages", topic.getMessages());
            } else {
                map.put("messages", "No messages in this topic");
            }

            for (Message m : topic.getMessages()) {
                System.out.println(m.getId() + "] " + m.getMessageContent());
            }

            map.put("topic", topic);
            map.put("display", "topic");

        } else {
            map.put("display", "notopic");
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

        Long idlong = new Long(1);
        User user = userRepository.findOne(idlong);
        newMessage.setUser(user);
        newMessage.setTopic(topic);

        topic.setDate(System.currentTimeMillis());

        topicRepository.save(topic);
        messageRepository.save(newMessage);

        return "redirect:/topic/" + id;
    }

    @RequestMapping(value = "/newtopic", method = RequestMethod.GET)
    String newTopicGet(ModelMap map, @ModelAttribute("topic") Topic topic) {
        map.put("display", "newtopic");
        return "offbase";
    }

    @RequestMapping(value = "newtopic", method = RequestMethod.POST)
    String newTopicPost(@ModelAttribute("topic") Topic topic,
                        ModelMap map, @RequestParam("firstMessage") String firstMessage) {
        Forum forum = forumRepository.findOne(new Long(1)); // there's only 1 forum so far, scalability son.
        topic.setDate(System.currentTimeMillis());
        topic.setForum(forum);

        topicRepository.save(topic);

        Message message = new Message();
        message.setTopic(topic);
        message.setUser(userRepository.findOne(new Long(1))); // only 1 user 'anonymous'
        message.setMessageContent(firstMessage);
        messageRepository.save(message);

        return "redirect:/";
    }

    @RequestMapping("/doallkinds")
    String execute() {
        return "";
    }
}


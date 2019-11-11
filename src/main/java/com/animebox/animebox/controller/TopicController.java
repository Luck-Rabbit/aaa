package com.animebox.animebox.controller;

import com.animebox.animebox.bean.circle.Topic;
import com.animebox.animebox.bean.circle.UserTopic;
import com.animebox.animebox.bean.user.User;
import com.animebox.animebox.service.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;


/**
 * @ClassName TopicController
 * @Description: TODO
 * @Author: LuckyRabbit
 * @create: 2019-10-20 21:11
 * @Version 1.0
 */
@Controller
public class TopicController {
    @Autowired
    ITopicService iTopicService;

    @RequestMapping(value = "/topic/{id}")
    public String topicInfo(@PathVariable("id") Integer id, Map<String,Object> map) {
        Topic topic = iTopicService.getTopicById(id);
        map.put("topic",topic);
        return "topic/topic";
    }


    @ResponseBody
    @RequestMapping(value = "/userLikeTopic")
    public Boolean topicAddUser(@RequestBody UserTopic userTopic, HttpSession session){
        if(session.getAttribute("loginUser") != null &&
                ((User) session.getAttribute("loginUser")).getIntUserId().equals(userTopic.getIntUserId())){
            System.out.println(userTopic);
            Boolean blnSuccess = iTopicService.userLikeTopic(userTopic);
            if(blnSuccess){
                return true;
            }
        }
        return false;
    }

    @PostMapping(value = "/newTopic")
    public String newTopic(@RequestParam("intCircleId")Integer intCircleId,
                           @RequestParam("intUserId")Integer intUserId,
                           @RequestParam("strBBCodeTopicContext")String strTopicContext,
                           @RequestParam("strTopicName") String strTopicName){
        Boolean blnCreateNewTopic = iTopicService.createNewTopic(intCircleId,intUserId, strTopicContext,strTopicName);
        System.out.println(blnCreateNewTopic);
        return "index";
    }
}
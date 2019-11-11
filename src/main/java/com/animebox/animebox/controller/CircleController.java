package com.animebox.animebox.controller;

import com.animebox.animebox.bean.circle.Circle;
import com.animebox.animebox.bean.circle.CircleUser;
import com.animebox.animebox.bean.circle.Topic;
import com.animebox.animebox.bean.user.User;
import com.animebox.animebox.bean.user.UserInfo;
import com.animebox.animebox.service.ICircleService;
import com.animebox.animebox.service.ICircleUserService;
import com.animebox.animebox.service.ITopicService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CircleController
 * @Description: TODO 尚未清楚BBCode是否有坏影响,后期进行改进
 * @Author: LuckyRabbit
 * @create: 2019-10-20 16:34
 * @Version 1.0
 */
@Controller
public class CircleController {

    @Autowired
    ICircleService iCircleService;

    @Autowired
    ITopicService iTopicService;

    @Autowired
    ICircleUserService iCircleUserService;
    /**
     * @Author LuckyRabbit
     * @Description //TODO 获得小组主页
     * @Date 12:59 2019/10/22
      * @param id 小组id
      * @param map 小组信息回传
     * @return java.lang.String
     **/
    @RequestMapping(value = "/circle/{id}")
    public String circleInfo(@PathVariable("id") Integer id, HttpSession session, Map<String,Object> map) {
        Circle circle = iCircleService.selectCircleById(id);
        List<UserInfo> circleNewUser = iCircleUserService.getCircleNewEightUser(circle.getIntCircleId());
        map.put("circle",circle);
        map.put("circleNewUser",circleNewUser);
        map.put("blnUserExistCircle",false);
        map.put("blnUserIsHeadman",false);
        if(session.getAttribute("loginUser") != null){
            User user = ((User)session.getAttribute("loginUser"));
            Boolean blnUserExistCircle = iCircleUserService.userExistCircle(circle.getIntCircleId(), user.getIntUserId());
            map.put("blnUserExistCircle",blnUserExistCircle);
            if(circle.getIntCircleHeadmanId().equals(user.getIntUserId())){
                map.put("blnUserIsHeadman",true);
            }
        }
        return "circle/circle";
    }

    /**
     * @Author LuckyRabbit
     * @Description //TODO 修改圈子数据
     * @Date 8:55 2019/10/31
      * @param id
  * @param map
     * @return java.lang.String
     **/
    @RequestMapping(value = "/circle/{id}/settings")
    public String topicSetting(@PathVariable("id") Integer id, Map<String,Object> map) {
        Circle circle = iCircleService.selectCircleById(id);
        map.put("circle",circle);
        return "circle/settings";
    }

    /**
     * @Author LuckyRabbit
     * @Description //TODO 尚未完成保存操作
     * @Date 11:21 2019/10/31
      * @param circle 
     * @return java.lang.String
     **/
    @PostMapping(value = "/circle/{id}/settings")
    public String updateCircleInfo(Circle circle,HttpSession session) {
        if(session.getAttribute("loginUser") != null){
            User user = ((User)session.getAttribute("loginUser"));
            if(circle.getIntCircleHeadmanId().equals(user.getIntUserId())){
                Boolean updateCircleSuccess = iCircleService.updateCircle(circle);
            }
        }
        String url = "redirect:/circle/"+circle.getIntCircleHeadmanId()+"/settings";
        return url;
    }
    /**
     * @Author LuckyRabbit
     * @Description //TODO 查看所有小组，分页回传
     * @Date 13:00 2019/10/22
      * @param map
     * @return java.lang.String
     **/
    @RequestMapping(value = "/circle/all")
    public String circleAll(Map<String,Object> map) {
        List<Circle> circles = iCircleService.selectCircleAll();
        map.put("circles",circles);
        return "circle/circleAll";
    }

    /**
     * @Author LuckyRabbit
     * @Description //TODO 小组主页
     * @Date 13:01 2019/10/22
      * @param map
     * @return java.lang.String
     **/
    @RequestMapping(value = "/circle/explore")
    public String circleIndex(@RequestParam(value="pageNum",defaultValue="0")int pageNum,Map<String, Object> map){
        PageInfo<Topic> hotTopicPageInfo = iTopicService.getHotTopic(pageNum);
        List<Circle> hotCircles = iCircleService.selectHotCircle();
        List<Circle> newCircles = iCircleService.selectNewCircle();
//        int subStartIndex=(int)(Math.random()*(hotCircles.size()-21));
//        List<Circle> hotCirclesSubList = hotCircles.subList(subStartIndex,subStartIndex+20);
        map.put("hotCircles",hotCircles);
        map.put("newCircles",newCircles);
        map.put("hotTopicPageInfo",hotTopicPageInfo);
        return "circle/explore";
    }

    /**
     * @Author LuckyRabbit
     * @Description //TODO 想应前端ajax回传加入小组的请求
     * @Date 13:04 2019/10/22
      * @param circleUser 封装回传json
      * @param session 判断回传用户id是否与session中保存的loginUser相同
     * @return java.lang.Boolean
     **/
    @ResponseBody
    @RequestMapping(value = "/circleAddUser")
    public Boolean circleAddUser(@RequestBody CircleUser circleUser,HttpSession session){
        if(session.getAttribute("loginUser") != null &&
                ((User) session.getAttribute("loginUser")).getIntUserId().equals(circleUser.getIntUserId())){
            Boolean blnSuccess = iCircleUserService.circleAddUser(circleUser.getIntCircleId(), circleUser.getIntUserId());
            System.out.println(circleUser);
            return blnSuccess;
        }
        return false;
    }

    @RequestMapping(value = "/circle/user/{id}")
    public String circlePeopleIndex(@PathVariable("id") Integer id,HttpSession session,Map<String, Object> map){
        map.put("uid",id);
        return "circle/circleUserIndex";
    }
}
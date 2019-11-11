package com.animebox.animebox.controller;

import com.animebox.animebox.bean.user.User;
import com.animebox.animebox.bean.user.UserInfo;
import com.animebox.animebox.service.imp.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;
/**
 * @ClassName UserInfo
 * @Description: TODO 用户信息
 * @Author: LuckyRabbit
 * @create: 2019-10-17 11:16
 * @Version 1.0
 */
@Controller
public class UserInfoController {

    @Autowired
    UserServiceImpl userService;

    /**
     * @Author LuckyRabbit
     * @Description //TODO 查询对应id用户个人主页
     * @Date 14:12 2019/10/17
      * @param id  用户id
  * @param map
     * @return java.lang.String
     **/
    @RequestMapping(value = "/user/info/{id}")
    public String userInfo(@PathVariable("id") Integer id,Map<String,Object> map) {
        UserInfo userInfo = userService.getUserInfo(id);
        map.put("userInfo",userInfo);
        return "user/userInfo";
    }

    /**
     * @Author LuckyRabbit
     * @Description //TODO 进入编辑个人主页信息页
     * @Date 14:12 2019/10/17
      * @param id 
      * @param map
     * @return java.lang.String
     **/
    @RequestMapping(value = "/user/info/edit/{id}")
    public String editUserInfo(@PathVariable("id") Integer id,Map<String,Object> map) {
        UserInfo userInfo = userService.getUserInfo(id);
        map.put("userInfo",userInfo);
        return "user/userEditInfo";
    }

    /**
     * @Author LuckyRabbit
     * @Description //TODO 更新用户信息------也许需要方法增强防止更改用户数据后session中的user没更改
     * @Date 14:49 2019/10/17
      * @param session
      * @param userInfo 用户信息，SpringMVC自动请求参数和入参对象的属性一一绑定，请求参数的名字和javaBean入参属性名是一样的
      * @param id
      * @param map
     * @return java.lang.String
     **/
    @PostMapping(value = "/user/info/edit/{id}")
    public String updateUserInfo(HttpSession session, UserInfo userInfo, @PathVariable("id") Integer id,Map<String,Object> map) {
        Boolean blnUpdateFlag = userService.updateUserInfo(userInfo,((User)session.getAttribute("loginUser")).getUserInfo());
        if(blnUpdateFlag){
            User user = ((User)session.getAttribute("loginUser"));
            user.setUserInfo(userInfo);
        }
        map.put("userInfo",userInfo);
        //无论更新与否都不应返回成功失败，只有特殊情况才能返回
//        map.put("updateFlag",blnUpdateFlag);
//        model.addAttribute("updateFlag",blnUpdateFlag);
//        session.setAttribute("loginUser",user);
        return "redirect:/user/info/{id}";
    }
}

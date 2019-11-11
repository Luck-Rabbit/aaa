package com.animebox.animebox.controller;

import com.animebox.animebox.bean.user.User;
import com.animebox.animebox.service.IMailService;
import com.animebox.animebox.service.IUserService;
import com.animebox.animebox.utils.CookieUtils;
import com.animebox.animebox.utils.SendMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import static com.animebox.animebox.utils.SendMessageUtil.getRandomCode;

/**
 * @ClassName UserInfo
 * @Description: TODO 登陆
 * @Author: LuckyRabbit
 * @create: 2019-10-17 11:16
 * @Version 1.0
 */
@Controller
public class LoginController {

    @Autowired
    IUserService iUserService;

    @Autowired
    private IMailService iMailService;

    /**
     * @Author LuckyRabbit
     * @Description //TODO 提供登陆页面
     * @Date 11:34 2019/10/17
      * @param request 判断是否有cookie
      * @param model 有cookie则向前台存入cookie中的数据
     * @return java.lang.String
     **/
    @RequestMapping(value = {"/user/login","/"})
    public String login(HttpServletRequest request, Model model){
        Cookie animeboxUser = CookieUtils.getCookie(request, "AnimeboxUser");
        if(animeboxUser != null){
            String strAnimeboxUser= animeboxUser.getValue();
            String strUserTelephoneOrEmail =strAnimeboxUser.split("-")[0];
            String strPassword =strAnimeboxUser.split("-")[1];
            model.addAttribute("strUserTelephoneOrEmail",strUserTelephoneOrEmail);
            model.addAttribute("strPassword",strPassword);
        }
        return  "user/login";
    }

    /**
     * @Author LuckyRabbit
     * @Description //TODO 验证登陆是否合法
     * @Date 11:31 2019/10/17
      * @param strUserTelephoneOrEmail 用户登陆账号，可为手机号，也可以为邮箱
      * @param strPassword 用户登陆密码
      * @param remember 是否勾选了记住密码的checkbox
      * @param map 存入用户登陆失败与否的信息
      * @param session 将登陆状态存入cookie
      * @param response
      * @param request
     * @return java.lang.String
     **/
    @PostMapping(value = {"/user/login"})
    public String loginValid(@RequestParam("username") String strUserTelephoneOrEmail, @RequestParam("password") String strPassword,String[] remember,
                             Map<String,Object> map, HttpSession session, HttpServletResponse response, HttpServletRequest request){
        User user = iUserService.userValid(strUserTelephoneOrEmail, DigestUtils.md5DigestAsHex(strPassword.getBytes()));
        if (user == null){
            map.put("msg","用户名密码错误");
            return  "redirect:/user/login";
        }else{
            session.setAttribute("loginUser",user);
            session.setAttribute("headImgUrl",user.getUserInfo().getStrUserHeadPortrait());
            if(remember != null){
                if(CookieUtils.cookieExist(request,"AnimeboxUser")){
                    CookieUtils.editCookie(request,response,"AnimeboxUser",strUserTelephoneOrEmail+"-"+strPassword,"localhost");
                }else{
                    CookieUtils.addCookie(response,"AnimeboxUser",strUserTelephoneOrEmail+"-"+strPassword,"localhost");
                }
            }
            String strUrl = "redirect:/user/info/"+user.getIntUserId();
            return strUrl;
        }
    }


}

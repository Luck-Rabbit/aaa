package com.animebox.animebox.controller;

import com.animebox.animebox.service.IMailService;
import com.animebox.animebox.service.IUserService;
import com.animebox.animebox.utils.SendMessageUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.regex.Pattern;

import static com.animebox.animebox.utils.SendMessageUtil.getRandomCode;

/**
 * @ClassName RegisterController
 * @Description: TODO
 * @Author: LuckyRabbit
 * @create: 2019-10-17 11:25
 * @Version 1.0
 */
@Controller
public class RegisterController {
    @Autowired
    IUserService iUserService;

    @Autowired
    private IMailService iMailService;

    @Autowired
    private DefaultKaptcha captchaProducer;

    @RequestMapping(value = {"/user/register"})
    public String register(){
        return  "user/register";
    }

//    @ResponseBody
//    @RequestMapping("/sendMessagePhone")
//    public Boolean sendMessagePhone(HttpSession session, @RequestBody  String phone){
//        Integer resultCode = SendMessageUtil.send("qq836344606","d41d8cd98f00b204e980",phone,"验证码:"+getRandomCode(6));
//        if(resultCode>0){
//            session.setAttribute("registerPhone",phone);
//            System.out.println(session.getAttribute("registerPhone"));
//            return true;
//        }
//        return false;
//    }
//
//    @ResponseBody
//    @RequestMapping("/sendMessageEmail")
//    public Boolean sendMessageEmail(HttpSession session, @RequestBody String email){
//        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
//        String message = "您的注册验证码为："+checkCode;
//        try {
//            iMailService.sendSimpleMail(email, "注册验证码", message);
//            session.setAttribute("registerEmail",email);
//            System.out.println(session.getAttribute("registerEmail"));
//            return true;
//        }catch (Exception e){
//            System.out.println("不懂为啥错误了啊");
//            return false;
//        }
//    }

    /**
     * @Author LuckyRabbit
     * @Description //TODO 接受前台的ajax请求 并判断数据类型，发送手机或邮件
     * @Date 17:02 2019/10/24
     * @param session
     * @param strUserTelephoneOrEmail
     * @return java.lang.Boolean
     **/
    @ResponseBody
    @RequestMapping("/sendMessage")
    public Boolean sendMessage(HttpSession session, @RequestBody String strUserTelephoneOrEmail){
        String strPhonePattern = "^\\d{11}$";
        boolean isMatchPhone = Pattern.matches(strPhonePattern,strUserTelephoneOrEmail);
        if(isMatchPhone){
            String strCode = getRandomCode(6);
            Integer resultCode = SendMessageUtil.send("qq836344606","d41d8cd98f00b204e980",strUserTelephoneOrEmail,"验证码:"+strCode);
            if(resultCode>0){
                session.setAttribute("strCode",strCode);
                return true;
            }
        }
        String strEmailPattern = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        boolean isMatchEmail = Pattern.matches(strEmailPattern,strUserTelephoneOrEmail);
        if(isMatchEmail){
            String strCode = getRandomCode(6);
            String message = "您的注册验证码为："+strCode;
            try {
                iMailService.sendSimpleMail(strUserTelephoneOrEmail, "Animebox验证码", message);
                session.setAttribute("strCode",strCode);
                return true;
            }catch (Exception e){
                System.out.println("不懂为啥错误了啊");
            }
        }
        return false;
    }

    /**
     * @Author LuckyRabbit
     * @Description //TODO 登陆验证
     * @Date 9:59 2019/10/25
     * @param strUserAlias 用户昵称
     * @param strUserTelephoneOrEmail 用户电话或邮箱地址
     * @param strUserPassword 用户密码
     * @param strCode 用户验证码
     * @param httpSession
     * @return java.lang.String
     **/
    @PostMapping(value = {"/user/register"})
    public String registerUser(@RequestParam("strUserAlias")String strUserAlias, @RequestParam("strUserTelephoneOrEmail") String strUserTelephoneOrEmail,
                               @RequestParam("strUserPassword") String strUserPassword, @RequestParam("strCode") String strCode, HttpSession httpSession){
        if(httpSession.getAttribute("strCode")!=null && httpSession.getAttribute("strCode").toString().equals(strCode)){
            boolean blnNewUserSuccess = iUserService.newUser(strUserAlias, strUserTelephoneOrEmail, strUserPassword);
            if(blnNewUserSuccess){
                return  "redirect:/user/login";
            }
        }
        return "redirect:/user/register";
    }

    @ResponseBody
    @RequestMapping("/testTelephoneOrEmailValid")
    public Boolean testTelephoneOrEmailValid(@RequestBody String strUserTelephoneOrEmail){
        Boolean blnValid = iUserService.telephoneOrEmailValid(strUserTelephoneOrEmail);
        return blnValid;
    }

    @RequestMapping("/user/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = captchaProducer.createText();
            httpServletRequest.getSession().setAttribute("vrifyCode", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = captchaProducer.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    @ResponseBody
    @RequestMapping("/imgValidControllerDefaultKaptcha")
    public Boolean imgvrifyControllerDefaultKaptcha(HttpServletRequest httpServletRequest, @RequestBody String strKaptcha){
//        System.out.println("123");
        String captchaId = (String) httpServletRequest.getSession().getAttribute("vrifyCode");
//        String parameter = httpServletRequest.getParameter("vrifyCode");
        System.out.println("Session  vrifyCode "+captchaId+" form vrifyCode "+strKaptcha);
        System.out.println(captchaId.equals(strKaptcha));
        if (!captchaId.equals(strKaptcha)) {
            return false;
        } else {
            return true;
        }
    }
}
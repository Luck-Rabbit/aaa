package com.animebox.animebox.controller;

import com.animebox.animebox.utils.CookieUtils;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName UserInfo
 * @Description: TODO 测试
 * @Author: LuckyRabbit
 * @create: 2019-10-17 11:16
 * @Version 1.0
 */
@Controller
public class TestController {
    @ResponseBody
    @RequestMapping("/testAddCookie")
    public String addCookie(HttpServletResponse response){
//        ModelAndView mAndView=new ModelAndView("show");
        CookieUtils.addCookie(response,"test_2","zyh-123","localhost");
        return "success";
    }

    @ResponseBody
    @RequestMapping("/testGetCookie")
    public String getCookie(HttpServletRequest request){
        String test_2 = CookieUtils.getCookieValue(request, "test_2");
        return test_2;
    }


    @Autowired
    private DefaultKaptcha captchaProducer;

    /**
     * 获取验证码 的 请求路径
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws Exception
     */
    @RequestMapping("/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{
//        byte[] captchaChallengeAsJpeg = null;
//        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
//        try {
//            //生产验证码字符串并保存到session中
//            String createText = captchaProducer.createText();
//            httpServletRequest.getSession().setAttribute("vrifyCode", createText);
//            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
//            BufferedImage challenge = captchaProducer.createImage(createText);
//            ImageIO.write(challenge, "jpg", jpegOutputStream);
//        } catch (IllegalArgumentException e) {
//            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
//            return;
//        }
//
//        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
//        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
//        httpServletResponse.setHeader("Cache-Control", "no-store");
//        httpServletResponse.setHeader("Pragma", "no-cache");
//        httpServletResponse.setDateHeader("Expires", 0);
//        httpServletResponse.setContentType("image/jpeg");
//        ServletOutputStream responseOutputStream =
//                httpServletResponse.getOutputStream();
//        responseOutputStream.write(captchaChallengeAsJpeg);
//        responseOutputStream.flush();
//        responseOutputStream.close();
    }


    /**
     * 验证的方法
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
    */
//    @ResponseBody
//    @RequestMapping("/imgvrifyControllerDefaultKaptcha")
//    public ModelAndView imgvrifyControllerDefaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
//        ModelAndView andView = new ModelAndView();
//        String captchaId = (String) httpServletRequest.getSession().getAttribute("vrifyCode");
//        String parameter = httpServletRequest.getParameter("vrifyCode");
//        System.out.println("Session  vrifyCode "+captchaId+" form vrifyCode "+parameter);
//        System.out.println(captchaId.equals(parameter));
//        if (!captchaId.equals(parameter)) {
//            andView.addObject("info", "错误的验证码");
//            andView.setViewName("index");
//        } else {
//            andView.addObject("info", "登录成功");
//            andView.setViewName("success");
//        }
//        return andView;
//    }

    @RequestMapping("/login")
    public String  login(){

        return "test";
    }
}

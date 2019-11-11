package com.animebox.animebox;

import com.animebox.animebox.bean.circle.Circle;
import com.animebox.animebox.dao.ICircleDao;
import com.animebox.animebox.dao.IUserInfoDao;
import com.animebox.animebox.service.ICircleService;
import com.animebox.animebox.service.IMailService;
import com.animebox.animebox.service.ITopicService;
import com.animebox.animebox.service.IUserService;
import com.animebox.animebox.utils.HTMLTemplateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnimeboxApplicationTests {

    @Autowired
    IUserService iUserService;

    @Autowired
    ICircleService iCircleService;

    @Autowired
    IUserInfoDao iUserInfoDao;

    @Autowired
    ITopicService iTopicService;

    @Autowired
    ICircleDao iCircleDao;


    @Autowired
    private IMailService iMailService;

    @Test
    public void contextLoads() {
//        boolean b = iUserService.UserValid("18159663582", "123456");
//        boolean b1 = iUserService.UserValid("836344606@qq.com", "123456");
//        System.out.println(b);
//        System.out.println(b1);

//        System.out.println(iUserInfoDao.selectUserAliasById(2));
//

//        Circle circle = iCircleService.selectCircleById(2);
//        circle.getStrUserAlias();
//        System.out.println(circle);
//
//        System.out.println(iTopicService.g|etTopicById(2));
//        int i = -1;
//        try{
//            i = iCircleDao.insertCircleUser();
//        }catch (DuplicateKeyException exception){
//            System.out.println("这逼尝试多次加入小组");
//        }
//        System.out.println(i);

//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

//        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
//        String message = "您的注册验证码为："+checkCode;
//        try {
//            iMailService.sendSimpleMail("zyh836344606@qq.com", "注册验证码", message);
//        }catch (Exception e){
//
//        }

//        String pattern = "^\\d{11}$";
//        boolean isMatch = Pattern.matches(pattern,"1815966358");
//        System.out.println(isMatch);

        Boolean bln =  iUserService.newUser("zyh","18159663591","18159663582");
        System.out.println(bln);
    }

    @Test
    public void testTemplateResolutionAttributes01() throws Exception {
        String template = "<p th:text='${title}'></p>";
        Map<String, Object> params = new HashMap<>();
        params.put("title", "Thymeleaf 渲染 HTML ---- Anoy");
        String output = HTMLTemplateUtil.render(template, params);
        System.out.println(output);
    }

}

package com.animebox.animebox;

import com.animebox.animebox.bean.user.User;
import com.animebox.animebox.bean.user.UserInfo;
import com.animebox.animebox.service.IUserService;
import com.animebox.animebox.utils.EncryptionUtil;
import com.animebox.animebox.utils.SendMessageUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.animebox.animebox.utils.SendMessageUtil.getRandomCode;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    IUserService iUserService;

    @Test
    public void contextLoads() {

    }

    @Test
    public void hashTest(){
//        System.out.println(EncryptionUtil.getHash("character-1", "SHA").equals("1469cbb52f041ccc94aeb82ddd9265a476d685d1"));
//        System.out.println(EncryptionUtil.getHash2("character-1", "SHA").equals("1469cbb52f041ccc94aeb82ddd9265a476d685d1"));
        System.out.println(EncryptionUtil.getHash3("staff-animation-253", "SHA"));
    }

    @Test
    public void testSendMessage(){
        //SendMessageUtil.send("SMS账户","接口秘钥","目标号码","发送内容");
        Integer resultCode = SendMessageUtil.send("qq836344606","d41d8cd98f00b204e980","18159663582","验证码:"+getRandomCode(6));
        System.out.println(SendMessageUtil.getMessage(resultCode));
    }

    @Test
    public void testAliYunSendMessage(){

    }

}
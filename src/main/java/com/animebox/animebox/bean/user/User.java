package com.animebox.animebox.bean.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
/**
 * @ClassName User
 * @Description: TODO 用户登陆
 * @Author: LuckyRabbit
 * @create: 2019-10-17 11:16
 * @Version 1.0
 */
public class User implements Serializable {

    @NotEmpty
    private Integer intUserId;
    @NotEmpty
    private String strUserTelephone;
    @Email
    private String strUserEmail;
    @NotEmpty
    private String strUserPassword;

    private UserInfo userInfo;

    @Override
    public String toString() {
        return "User{" +
                "intUserId=" + intUserId +
                ", strUserTelephone='" + strUserTelephone + '\'' +
                ", strUserEmail='" + strUserEmail + '\'' +
                ", strUserPassword='" + strUserPassword + '\'' +
                '}';
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Integer getIntUserId() {
        return intUserId;
    }

    public void setIntUserId(Integer intUserId) {
        this.intUserId = intUserId;
    }

    public String getStrUserTelephone() {
        return strUserTelephone;
    }

    public void setStrUserTelephone(String strUserTelephone) {
        this.strUserTelephone = strUserTelephone;
    }

    public String getStrUserEmail() {
        return strUserEmail;
    }

    public void setStrUserEmail(String strUserEmail) {
        this.strUserEmail = strUserEmail;
    }

    public String getStrUserPassword() {
        return strUserPassword;
    }

    public void setStrUserPassword(String strUserPassword) {
        this.strUserPassword = strUserPassword;
    }
}

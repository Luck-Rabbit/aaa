package com.animebox.animebox.bean.user;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName UserInfo
 * @Description: TODO 用户主页信息
 * @Author: LuckyRabbit
 * @create: 2019-10-17 11:16
 * @Version 1.0
 */
public class UserInfo implements Serializable {
    @NotEmpty
    private Integer intUserId;
    @NotEmpty
    private String strUserAlias;
    @NotEmpty
    private String strUserHeadPortrait;
//    @NotEmpty
    private int intUserSex;
//    @DateTimeFormat
    private String UserBirth;
//    @NotEmpty
    private String strUserIntro;
//    @NotEmpty
    private String strUserTag;

    public Integer getIntUserId() {
        return intUserId;
    }

    public void setIntUserId(Integer intUserId) {
        this.intUserId = intUserId;
    }

    public String getStrUserAlias() {
        return strUserAlias;
    }

    public void setStrUserAlias(String strUserAlias) {
        this.strUserAlias = strUserAlias;
    }

    public String getStrUserHeadPortrait() {
        return strUserHeadPortrait;
    }

    public void setStrUserHeadPortrait(String strUserHeadPortrait) {
        this.strUserHeadPortrait = strUserHeadPortrait;
    }

    public int getIntUserSex() {
        return intUserSex;
    }

    public void setIntUserSex(int intUserSex) {
        this.intUserSex = intUserSex;
    }

    public String getUserBirth() {
        return UserBirth;
    }

    public void setUserBirth(String userBirth) {
        UserBirth = userBirth;
    }

    public String getStrUserIntro() {
        return strUserIntro;
    }

    public void setStrUserIntro(String strUserIntro) {
        this.strUserIntro = strUserIntro;
    }

    public String getStrUserTag() {
        return strUserTag;
    }

    public void setStrUserTag(String strUserTag) {
        this.strUserTag = strUserTag;
    }
}

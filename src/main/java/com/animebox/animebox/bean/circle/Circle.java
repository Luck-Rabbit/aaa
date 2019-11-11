package com.animebox.animebox.bean.circle;

import java.util.List;

/**
 * @ClassName Circle
 * @Description: TODO 圈子类
 * @Author: LuckyRabbit
 * @create: 2019-10-20 16:37
 * @Version 1.0
 */
public class Circle {
    private Integer intCircleId;
    private String strCircleName;
    private String strCircleImgUrl;
    private String strCircleContent;
    private String intCircleTag;
    private Integer strCircleUserCount;
    private Integer intCircleHeadmanId;
    private String strUserAlias;
    private String circleCreateTime;
    private String circleModifiedTime;

    public String getCircleModifiedTime() {
        return circleModifiedTime;
    }

    public void setCircleModifiedTime(String circleModifiedTime) {
        this.circleModifiedTime = circleModifiedTime;
    }

    private List<Topic> topicList;

    public Integer getIntCircleId() {
        return intCircleId;
    }

    public void setIntCircleId(Integer intCircleId) {
        this.intCircleId = intCircleId;
    }

    public String getStrCircleName() {
        return strCircleName;
    }

    public void setStrCircleName(String strCircleName) {
        this.strCircleName = strCircleName;
    }

    public String getStrCircleImgUrl() {
        return strCircleImgUrl;
    }

    public void setStrCircleImgUrl(String strCircleImgUrl) {
        this.strCircleImgUrl = strCircleImgUrl;
    }

    public String getStrCircleContent() {
        return strCircleContent;
    }

    public void setStrCircleContent(String strCircleContent) {
        this.strCircleContent = strCircleContent;
    }

    public String getIntCircleTag() {
        return intCircleTag;
    }

    public void setIntCircleTag(String intCircleTag) {
        this.intCircleTag = intCircleTag;
    }

    public Integer getStrCircleUserCount() {
        return strCircleUserCount;
    }

    public void setStrCircleUserCount(Integer strCircleUserCount) {
        this.strCircleUserCount = strCircleUserCount;
    }

    public Integer getIntCircleHeadmanId() {
        return intCircleHeadmanId;
    }

    public void setIntCircleHeadmanId(Integer intCircleHeadmanId) {
        this.intCircleHeadmanId = intCircleHeadmanId;
    }

    public String getStrUserAlias() {
        return strUserAlias;
    }

    public void setStrUserAlias(String strUserAlias) {
        this.strUserAlias = strUserAlias;
    }

    public String getCircleCreateTime() {
        return circleCreateTime;
    }

    public void setCircleCreateTime(String circleCreateTime) {
        this.circleCreateTime = circleCreateTime;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }
}
package com.animebox.animebox.bean.circle;

import java.security.PrivateKey;
import java.util.List;

/**
 * @ClassName Topic
 * @Description: TODO
 * @Author: LuckyRabbit
 * @create: 2019-10-20 19:45
 * @Version 1.0
 */
public class Topic {
    private Integer intTopicId;
    private Integer intCircleId;
    private String strTopicName;
    private String strTopicContent;
    private Integer intUserLikeCount;
    private Integer intTopicHeadmanId;
    private String strUserAlias;
    private String topicCreateTime;
    private String topicLastLikeTime;
    private Integer intTopicReplyCount;
    private List<Reply> replyList;

    public Integer getIntTopicId() {
        return intTopicId;
    }

    public void setIntTopicId(Integer intTopicId) {
        this.intTopicId = intTopicId;
    }

    public Integer getIntCircleId() {
        return intCircleId;
    }

    public void setIntCircleId(Integer intCircleId) {
        this.intCircleId = intCircleId;
    }

    public String getStrTopicName() {
        return strTopicName;
    }

    public void setStrTopicName(String strTopicName) {
        this.strTopicName = strTopicName;
    }

    public String getStrTopicContent() {
        return strTopicContent;
    }

    public void setStrTopicContent(String strTopicContent) {
        this.strTopicContent = strTopicContent;
    }

    public Integer getIntUserLikeCount() {
        return intUserLikeCount;
    }

    public void setIntUserLikeCount(Integer intUserLikeCount) {
        this.intUserLikeCount = intUserLikeCount;
    }

    public Integer getIntTopicHeadmanId() {
        return intTopicHeadmanId;
    }

    public void setIntTopicHeadmanId(Integer intTopicHeadmanId) {
        this.intTopicHeadmanId = intTopicHeadmanId;
    }

    public String getStrUserAlias() {
        return strUserAlias;
    }

    public void setStrUserAlias(String strUserAlias) {
        this.strUserAlias = strUserAlias;
    }

    public String getTopicCreateTime() {
        return topicCreateTime;
    }

    public void setTopicCreateTime(String topicCreateTime) {
        this.topicCreateTime = topicCreateTime;
    }

    public String getTopicLastLikeTime() {
        return topicLastLikeTime;
    }

    public void setTopicLastLikeTime(String topicLastLikeTime) {
        this.topicLastLikeTime = topicLastLikeTime;
    }

    public Integer getIntTopicReplyCount() {
        return intTopicReplyCount;
    }

    public void setIntTopicReplyCount(Integer intTopicReplyCount) {
        this.intTopicReplyCount = intTopicReplyCount;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }
}
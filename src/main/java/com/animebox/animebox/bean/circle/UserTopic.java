package com.animebox.animebox.bean.circle;

/**
 * @ClassName UserTopic
 * @Description: TODO
 * @Author: LuckyRabbit
 * @create: 2019-10-22 13:34
 * @Version 1.0
 */
public class UserTopic {
    private Integer intTopicId;
    private Integer intUserId;
    //喜欢为1 不喜欢为0
    private Integer intUserLike;

    @Override
    public String toString() {
        return "UserTopic{" +
                "intTopicId=" + intTopicId +
                ", intUserId=" + intUserId +
                ", intUserLike=" + intUserLike +
                '}';
    }

    public Integer getIntTopicId() {
        return intTopicId;
    }

    public void setIntTopicId(Integer intTopicId) {
        this.intTopicId = intTopicId;
    }

    public Integer getIntUserId() {
        return intUserId;
    }

    public void setIntUserId(Integer intUserId) {
        this.intUserId = intUserId;
    }

    public Integer getIntUserLike() {
        return intUserLike;
    }

    public void setIntUserLike(Integer intUserLike) {
        this.intUserLike = intUserLike;
    }
}
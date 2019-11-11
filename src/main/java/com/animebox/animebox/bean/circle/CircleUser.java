package com.animebox.animebox.bean.circle;

/**
 * @ClassName CircleUser
 * @Description: TODO 对应数据库CircleUser表
 * @Author: LuckyRabbit
 * @create: 2019-10-21 22:08
 * @Version 1.0
 */
public class CircleUser {
    private Integer intCircleId;
    private Integer intUserId;
    private String strUserAddCircleTime;

    public String getStrUserAddCircleTime() {
        return strUserAddCircleTime;
    }

    public void setStrUserAddCircleTime(String strUserAddCircleTime) {
        this.strUserAddCircleTime = strUserAddCircleTime;
    }

    @Override
    public String toString() {
        return "CircleUser{" +
                "intCircleId=" + intCircleId +
                ", intUserId=" + intUserId +
                '}';
    }

    public Integer getIntCircleId() {
        return intCircleId;
    }

    public void setIntCircleId(Integer intCircleId) {
        this.intCircleId = intCircleId;
    }

    public Integer getIntUserId() {
        return intUserId;
    }

    public void setIntUserId(Integer intUserId) {
        this.intUserId = intUserId;
    }
}
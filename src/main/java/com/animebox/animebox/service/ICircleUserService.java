package com.animebox.animebox.service;

import com.animebox.animebox.bean.circle.Circle;
import com.animebox.animebox.bean.user.UserInfo;

import java.util.List;

public interface ICircleUserService {
    public Boolean circleAddUser(Integer intCircleId,Integer intUserId);

    public Boolean userExistCircle(Integer intCircleId,Integer intUserId);

    public List<UserInfo> getCircleNewEightUser(Integer intCircleId);
}

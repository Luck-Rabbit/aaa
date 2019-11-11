package com.animebox.animebox.service.imp;

import com.animebox.animebox.bean.circle.CircleUser;
import com.animebox.animebox.bean.user.UserInfo;
import com.animebox.animebox.dao.ICircleDao;
import com.animebox.animebox.dao.ICircleUserDao;
import com.animebox.animebox.service.ICircleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName CircleUserServiceImpl
 * @Description: TODO
 * @Author: LuckyRabbit
 * @create: 2019-10-31 15:35
 * @Version 1.0
 */
@Service
public class CircleUserServiceImpl implements ICircleUserService {
    @Autowired
    ICircleUserDao iCircleUserDao;

    @Autowired
    ICircleDao iCircleDao;
    @Override
    public Boolean circleAddUser(Integer intCircleId, Integer intUserId) {
        List<CircleUser> circleUsers = iCircleUserDao.selectUserExistCircle(intCircleId, intUserId);
        if(circleUsers.size()>0){
            int i = iCircleUserDao.deleteCircleUser(intCircleId, intUserId);
            if(i==1){
                return true;
            }else{
                return false;
            }
        }else{
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String gmt_create=dateFormat.format(new Date());
            String gmt_modified=dateFormat.format(new Date());
            int i = iCircleUserDao.insertCircleUser(intCircleId, intUserId, gmt_create, gmt_modified);
            if(i==1){
                return true;
            }else{
                return false;
            }
        }
    }

    @Override
    public Boolean userExistCircle(Integer intCircleId, Integer intUserId) {
        List<CircleUser> circleUsers = iCircleUserDao.selectUserExistCircle(intCircleId, intUserId);
        if(circleUsers.size()>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<UserInfo> getCircleNewEightUser(Integer intCircleId) {
        List<UserInfo> userInfos = iCircleUserDao.selectCircleNewEightUser(intCircleId);
        return userInfos;
    }
}
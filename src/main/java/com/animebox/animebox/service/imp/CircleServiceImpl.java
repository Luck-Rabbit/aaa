package com.animebox.animebox.service.imp;

import com.animebox.animebox.algorithm.CircleHotAlgorithm;
import com.animebox.animebox.bean.circle.Circle;
import com.animebox.animebox.bean.circle.CircleUser;
import com.animebox.animebox.dao.ICircleDao;
import com.animebox.animebox.dao.IUserLoginDao;
import com.animebox.animebox.service.ICircleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName CircleServiceImpl
 * @Description: TODO
 * @Author: LuckyRabbit
 * @create: 2019-10-20 16:47
 * @Version 1.0
 */
@Service
public class CircleServiceImpl implements ICircleService {
    @Autowired
    ICircleDao iCircleDao;

    @Override
    public Circle selectCircleById(Integer intCircleId) {
        Circle circle = iCircleDao.selectCircleById(intCircleId);
        return circle;
    }

    @Override
    public List<Circle> selectHotCircle() {
        Double dblAvgCircleUserCount = CircleHotAlgorithm.DBL_AVG_CIRCLE_USER_COUNT;
        List<Circle> hotCircles = iCircleDao.selectHotCircle(dblAvgCircleUserCount);
        return hotCircles;
    }

    @Override
    public List<Circle> selectNewCircle() {
        List<Circle> newCircles = iCircleDao.selectNewCircle();
        return newCircles;
    }

    @Override
    public List<Circle> selectCircleAll() {
        List<Circle> circles = iCircleDao.selectCircleAll();
        return circles;
    }





    @Override
    public Boolean updateCircle(Circle circle) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String gmt_modified=dateFormat.format(new Date());
        circle.setCircleModifiedTime(gmt_modified);
        int i = iCircleDao.updateCircle(circle);
        if(i>0){
            return true;
        }else{
            return false;
        }
    }
}
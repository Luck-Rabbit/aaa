package com.animebox.animebox.scheduled;

import com.animebox.animebox.algorithm.CircleHotAlgorithm;
import com.animebox.animebox.dao.ICircleDao;
import com.animebox.animebox.service.IAlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName LikeScheduled
 * @Description: TODO 定时将redis中缓存的点赞数据写入数据库
 * @Author: LuckyRabbit
 * @create: 2019-10-20 19:34
 * @Version 1.0
 */
@Component
public class LikeScheduled {

    @Autowired
    IAlgorithmService iAlgorithmService;

    @Scheduled(cron = "0/59 0/5 * * * ? ")
    public void timerToNow(){
        System.out.println(new Date());
    }

    /**
     * @Author LuckyRabbit
     * @Description //TODO 每1小时刷新一次热度评定线
     * @Date 15:18 2019/10/21
      * @param  
     * @return void
     **/
    @Scheduled(cron = "0/59 0/5 * * * ? ")
    public void setCircleHotAlgorithm(){
        double circleHotValue = iAlgorithmService.getCircleHotValue();
        CircleHotAlgorithm.DBL_AVG_CIRCLE_USER_COUNT = circleHotValue;
    }
}
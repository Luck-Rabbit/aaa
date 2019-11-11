package com.animebox.animebox.service.imp;

import com.animebox.animebox.dao.ICircleDao;
import com.animebox.animebox.service.IAlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName AlgorithmServiceImpl
 * @Description: TODO
 * @Author: LuckyRabbit
 * @create: 2019-10-21 15:26
 * @Version 1.0
 */
@Service
public class AlgorithmServiceImpl implements IAlgorithmService {
    @Autowired
    ICircleDao iCircleDao;

    @Override
    public double getCircleHotValue() {
        Double dblAvgCircleUserCount = iCircleDao.selectAvgCircleUserCount();
        return dblAvgCircleUserCount;
    }
}
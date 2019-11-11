package com.animebox.animebox.service;

import com.animebox.animebox.bean.circle.Circle;

import java.util.List;

public interface ICircleService {
    public Circle selectCircleById(Integer intCircleId);

    public List<Circle> selectHotCircle();

    public List<Circle> selectNewCircle();

    public List<Circle> selectCircleAll();



    public Boolean updateCircle(Circle circle);
}

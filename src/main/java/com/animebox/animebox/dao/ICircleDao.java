package com.animebox.animebox.dao;

import com.animebox.animebox.bean.circle.Circle;
import com.animebox.animebox.bean.circle.CircleUser;
import com.animebox.animebox.bean.user.UserInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import java.util.List;

@Mapper
public interface ICircleDao {
    @Select("select circle_id,circle_name,circle_content,circle_img_url,circle_tag_id,circle_user_count,circle_headman_id,gmt_create from circle where circle_id=#{intCircleId} ")
    @Results(id = "circleMap",value = {
            @Result(id = true,column = "circle_id",property = "intCircleId"),
            @Result(column = "circle_name",property = "strCircleName"),
            @Result(column = "circle_img_url",property = "strCircleImgUrl"),
            @Result(column = "circle_content",property = "strCircleContent"),
            @Result(column = "circle_tag_id",property = "intCircleTag"),
            @Result(column = "circle_user_count",property = "strCircleUserCount"),
            @Result(column = "circle_headman_id",property = "intCircleHeadmanId"),
            @Result(column = "gmt_create",property = "circleCreateTime"),
            @Result(property="strUserAlias",column ="circle_headman_id",one = @One(select = "com.animebox.animebox.dao.IUserInfoDao.selectUserAliasById",fetchType=FetchType.EAGER)),
            @Result(property="topicList",column ="circle_id",many = @Many(select = "com.animebox.animebox.dao.ITopicDao.selectTopicByCircleId",fetchType=FetchType.LAZY))
    })
    public Circle selectCircleById(Integer intCircleId);

    @Select("select circle_id,circle_name,circle_img_url,circle_tag_id,circle_user_count from circle ")
    @Results(id = "circleAllMap",value = {
            @Result(id = true,column = "circle_id",property = "intCircleId"),
            @Result(column = "circle_name",property = "strCircleName"),
            @Result(column = "circle_img_url",property = "strCircleImgUrl"),
            @Result(column = "circle_tag_id",property = "intCircleTag"),
            @Result(column = "circle_user_count",property = "strCircleUserCount")
    })
    public List<Circle> selectCircleAll();

    @Select("select circle_id,circle_name,circle_img_url,circle_tag_id,circle_user_count from circle where circle_user_count>#{dblAvgCircleUserCount} LIMIT 0,15")
    @ResultMap("circleAllMap")
    public List<Circle> selectHotCircle(Double dblAvgCircleUserCount);

    @Select("select circle_id,circle_name,circle_img_url,circle_tag_id,circle_user_count from circle ORDER BY gmt_create DESC LIMIT 0,10")
    @ResultMap("circleAllMap")
    public List<Circle> selectNewCircle();

    @Select("select avg(circle_user_count) as avg_circle_user_count from circle where circle_user_count>1")
    @Results(id = "avgCircleUserCountMap",value = {
            @Result(column = "avg_circle_user_count",property = "java.lang.Double")
    })
    public Double selectAvgCircleUserCount();


    /**\
     * 获取不到strCircleName
     * @param circle
     * @return
     */
    @Update("UPDATE circle SET circle_name=#{strCircleName},circle_content=#{strCircleContent}," +
            "circle_tag_id=#{intCircleTag},circle_img_url=#{strCircleImgUrl},gmt_modified=#{circleModifiedTime} WHERE circle_id= #{intCircleId} ")
    public int updateCircle(Circle circle);


}

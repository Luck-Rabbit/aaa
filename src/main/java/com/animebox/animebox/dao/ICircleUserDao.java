package com.animebox.animebox.dao;

import com.animebox.animebox.bean.circle.CircleUser;
import com.animebox.animebox.bean.user.UserInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @ClassName ICircleUserDao
 * @Description: TODO
 * @Author: LuckyRabbit
 * @create: 2019-10-31 15:33
 * @Version 1.0
 */
@Mapper
public interface ICircleUserDao {
    @Insert("insert IGNORE  into circle_user (circle_id,user_id,gmt_create,gmt_modified) " +
            "VALUES (#{intCircleId},#{intUserId},#{gmt_create},#{gmt_modified})")
    public int insertCircleUser(Integer intCircleId, Integer intUserId,String gmt_create,String gmt_modified);

    @Delete("DELETE FROM circle_user WHERE circle_id = #{intCircleId} and user_id = #{intUserId}")
    public int deleteCircleUser(Integer intCircleId, Integer intUserId);

    @Select("select circle_id,user_id,gmt_create from circle_user where circle_id = #{intCircleId} and user_id = #{intUserId}")
    @Results(id = "circleUserMap",value = {
            @Result(column = "circle_id",property = "intCircleId"),
            @Result(column = "user_id",property = "intUserId"),
            @Result(column = "gmt_create",property = "strUserAddCircleTime")
    })
    public List<CircleUser> selectUserExistCircle(Integer intCircleId, Integer intUserId);

    @Select("select user_id,user_alias,user_head_portrait from user_information where user_id in (select user_id from circle_user where circle_id=#{intCircleId} ORDER BY gmt_modified desc ) LIMIT 0,8")
    @Results(id = "userBaseInfoMap",value = {
            @Result(id = true,column = "user_id",property = "intUserId"),
            @Result(column = "user_alias",property = "strUserAlias"),
            @Result(column = "user_head_portrait",property = "strUserHeadPortrait")
    })
    public List<UserInfo> selectCircleNewEightUser(Integer intCircleId);
}
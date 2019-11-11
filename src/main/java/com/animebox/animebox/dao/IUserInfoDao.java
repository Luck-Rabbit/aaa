package com.animebox.animebox.dao;

import com.animebox.animebox.bean.user.UserInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface IUserInfoDao {
    /**
     * @Author LuckyRabbit
     * @Description //TODO 根据传入的用户id返回用户信息
     * @Date 14:32 2019/10/17
      * @param intUserId
     * @return com.animebox.animebox.bean.user.UserInfo
     **/
    @Select("select user_id,user_alias,user_head_portrait,user_sex,user_birth,user_intro,user_tag from user_information where user_id=#{intUserId} ")
    @Results(id = "userMap",value = {
            @Result(id = true,column = "user_id",property = "intUserId"),
            @Result(column = "user_alias",property = "strUserAlias"),
            @Result(column = "user_head_portrait",property = "strUserHeadPortrait"),
            @Result(column = "user_sex",property = "intUserSex"),
            @Result(column = "user_birth",property = "userBirth"),
            @Result(column = "user_intro",property = "strUserIntro"),
            @Result(column = "user_tag",property = "strUserTag")
    })
    public UserInfo selectUserInfoById(Integer intUserId);

    @Select("select user_id,user_alias,user_head_portrait,user_sex,user_birth,user_intro,user_tag from user_information where user_id=#{intUserId} ")
    @Results(id = "userBaseInfoMap",value = {
            @Result(id = true,column = "user_id",property = "intUserId"),
            @Result(column = "user_alias",property = "strUserAlias"),
            @Result(column = "user_head_portrait",property = "strUserHeadPortrait")
    })
    public UserInfo selectUserBaseInfoById(Integer intUserId);

/**
 * @Author LuckyRabbit
 * @Description //TODO 根据传入的userInfo更改用户的基础信息
 * @Date 15:04 2019/10/31
  * @param userInfo 
 * @return java.lang.Integer
 **/
    @Update("update user_information set user_alias=#{strUserAlias},user_head_portrait=#{strUserHeadPortrait},user_sex=#{intUserSex},user_birth=#{userBirth},user_tag=#{strUserTag} where user_id=#{intUserId}")
    public Integer updateUserBasicInfo(UserInfo userInfo);

    /**
     * @Author LuckyRabbit
     * @Description //TODO 根据传入的userInfo更改用户的额外信息
     * @Date 14:32 2019/10/17
     * @param strUserInfo
     * @return com.animebox.animebox.bean.user.UserInfo
     **/
    @Update("update user_information set user_intro=#{strUserIntro} where user_id=#{intUserId}")
    public Integer updateUserExInfo(UserInfo strUserInfo);

    @Select("select user_alias from user_information where user_id=#{intUserId} ")
    @Results(id = "strUserAlias",value = {
            @Result(column = "user_alias",property = "strUserAlias")
    })
    public String selectUserAliasById(Integer intUserId);

    @Insert("insert IGNORE into user_information(user_id,user_alias,gmt_create,gmt_modified) values(#{intUserId},#{strUserAlias},#{gmt_create},#{gmt_modified})")
    public Integer insertNewUserInfoOnlyUserAlias(Integer intUserId,String strUserAlias,String gmt_create,String gmt_modified);
}

package com.animebox.animebox.dao;

import com.animebox.animebox.bean.user.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface IUserLoginDao {

    //    @Select("select * from animebox_user where user_telephone=18159663582 and user_password='123456'")
    @Select("select * from animebox_user where user_telephone=#{intUserTelephone} and user_password=#{strUserPassword}")
    @Results(id = "userMap", value = {
            @Result(id = true, column = "user_id", property = "intUserId"),
            @Result(column = "user_telephone", property = "strUserTelephone"),
            @Result(column = "user_email", property = "strUserEmail"),
            @Result(column = "user_password", property = "strUserPassword"),
            @Result(property = "userInfo", column = "user_id", one = @One(select = "com.animebox.animebox.dao.IUserInfoDao.selectUserInfoById", fetchType = FetchType.LAZY))
})
    public List<User> selectUserLoginTelephoneValid(String intUserTelephone, String strUserPassword);

    @Select("select * from animebox_user where user_email=#{strUserEmail} and user_password=#{strUserPassword}")
    @ResultMap("userMap")
    public List<User> selectUserLoginEmailValid(String strUserEmail, String strUserPassword);

    @Select("select * from animebox_user where user_telephone=#{intUserTelephone}")
    @ResultMap("userMap")
    public List<User> selectTelephoneValid(String intUserTelephone);

    @Select("select * from animebox_user where user_email=#{strUserEmail}")
    @ResultMap("userMap")
    public List<User> selectEmailValid(String strUserEmail);

    @Insert("insert IGNORE into animebox_user(user_telephone,user_password,gmt_create,gmt_modified) values(#{user.strUserTelephone},#{user.strUserPassword},#{gmt_create},#{gmt_modified})")
    @SelectKey(statement = "select LAST_INSERT_ID()",  keyProperty = "user.intUserId", before = false, resultType = Integer.class)
    public Integer insertNewUserByPhone(User user, String gmt_create, String gmt_modified);

    @Insert("insert IGNORE into animebox_user(user_email,user_password,gmt_create,gmt_modified) values(#{user.strUserEmail},#{user.strUserPassword},#{gmt_create},#{gmt_modified})")
    @SelectKey(statement = "select LAST_INSERT_ID()",  keyProperty = "user.intUserId", before = false, resultType = Integer.class)
    public Integer insertNewUserByEmail(User user,String gmt_create, String gmt_modified);
//    @Select("select * from
//    @Select("select * from animebox_user where user_email=#{strUserEmail} and user_password=#{strUserPassword}")
//    @ResultMap("userMap")
//    public List<User> selectUserLoginValid(String strUserEmail, String strUserPassword);

}

package com.animebox.animebox.dao;

import com.animebox.animebox.bean.circle.BasicTopic;
import com.animebox.animebox.bean.circle.Circle;
import com.animebox.animebox.bean.circle.Topic;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @ClassName ITopicDao
 * @Description: TODO
 * @Author: LuckyRabbit
 * @create: 2019-10-20 19:54
 * @Version 1.0
 */
@Mapper
public interface ITopicDao {
    @Select("select topic_id,circle_id,topic_name,topic_content,user_like_count,topic_headman_id,gmt_create,gmt_modified,topic_reply_count from topic where topic_id=#{intTopicId} ")
    @Results(id = "circleMap",value = {
            @Result(id = true,column = "topic_id",property = "intTopicId"),
            @Result(column = "circle_id",property = "intCircleId"),
            @Result(column = "topic_name",property = "strTopicName"),
            @Result(column = "topic_content",property = "strTopicContent"),
            @Result(column = "user_like_count",property = "intUserLikeCount"),
            @Result(column = "topic_headman_id",property = "intTopicHeadmanId"),
            @Result(column = "gmt_create",property = "topicCreateTime"),
            @Result(column = "gmt_modified",property = "topicLastLikeTime"),
            @Result(column = "topic_reply_count",property = "intTopicReplyCount"),
            @Result(property="strUserAlias",column ="topic_headman_id",one = @One(select = "com.animebox.animebox.dao.IUserInfoDao.selectUserAliasById",fetchType= FetchType.EAGER)),
            @Result(property="replyList",column ="topic_id",many = @Many(select = "com.animebox.animebox.dao.IReplyDao.selectReplyByTopicId",fetchType= FetchType.LAZY))
    })
    public Topic selectTopicByTopicId(Integer intTopicId);

    @Select("select topic_id,circle_id,topic_name,topic_content,user_like_count,topic_headman_id,topic_reply_count,gmt_create,gmt_modified from topic where circle_id=#{intCircleId} ")
    @ResultMap("circleMap")
    public List<Topic> selectTopicByCircleId(Integer intCircleId);

    @Select("select topic_id,circle_id,topic_name,topic_content,user_like_count,topic_headman_id,gmt_create,gmt_modified,topic_reply_count from topic ORDER BY user_like_count desc ")
    @Results(id = "circleHotMap",value = {
            @Result(id = true,column = "topic_id",property = "intTopicId"),
            @Result(column = "circle_id",property = "intCircleId"),
            @Result(column = "topic_name",property = "strTopicName"),
            @Result(column = "topic_content",property = "strTopicContent"),
            @Result(column = "user_like_count",property = "intUserLikeCount"),
            @Result(column = "topic_headman_id",property = "intTopicHeadmanId"),
            @Result(column = "gmt_create",property = "topicCreateTime"),
            @Result(column = "gmt_modified",property = "topicLastLikeTime"),
            @Result(column = "topic_reply_count",property = "intTopicReplyCount"),
            @Result(property="strUserAlias",column ="topic_headman_id",one = @One(select = "com.animebox.animebox.dao.IUserInfoDao.selectUserAliasById",fetchType= FetchType.EAGER))
    })
    public List<Topic> selectHotTopic();
//    public List<Topic> selectHotTopic(Integer intStart,Integer intEnd);

    public List<Topic> selectUserLikeTopic();

    @Insert("insert IGNORE  into user_like_topic (topic_id,user_id,gmt_create,gmt_modified)" +
            "VALUES (#{intTopicId},#{intUserId},#{gmt_create},#{gmt_modified})")
    public int insertUserLikeTopic(Integer intTopicId, Integer intUserId, String gmt_create, String gmt_modified);

    @Delete("DELETE from user_like_topic WHERE topic_id=#{intTopicId} AND user_id =#{intUserId}")
    public int deleteUserLikeTopic(Integer intTopicId, Integer intUserId);

    @Insert("insert IGNORE into topic(circle_id,topic_name,topic_content,topic_headman_id,gmt_create,gmt_modified) values(#{intCircleId}, #{strTopicName}" +
            ", #{strTopicContext},#{intUserId},#{gmt_create},#{gmt_modified});")
    public int insertNewTopic(Integer intCircleId,Integer intUserId,String strTopicContext,String strTopicName, String gmt_create, String gmt_modified);
}
package com.animebox.animebox.dao;

import com.animebox.animebox.bean.circle.Reply;
import com.animebox.animebox.bean.circle.Topic;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface IReplyDao {
    @Select("select reply_id,topic_id,reply_target_id,reply_resouce_id,reply_content,user_like_count,gmt_create from reply where topic_id=#{intTopicId} ")
    @Results(id = "replyMap",value = {
            @Result(id = true,column = "reply_id",property = "intReplyId"),
            @Result(column = "topic_id",property = "intTopicId"),
            @Result(column = "reply_target_id",property = "intReplyTargetId"),
            @Result(column = "reply_resouce_id",property = "intReplyResourceId"),
            @Result(column = "reply_content",property = "strReplyContent"),
            @Result(column = "user_like_count",property = "strUserLikeCount"),
            @Result(column = "gmt_create",property = "strReplyTime")
    })
    public List<Reply> selectReplyByTopicId(Integer intTopicId);
}

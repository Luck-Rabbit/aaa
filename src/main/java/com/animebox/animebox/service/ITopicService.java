package com.animebox.animebox.service;

import com.animebox.animebox.bean.circle.Topic;
import com.animebox.animebox.bean.circle.UserTopic;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ITopicService {

    public Topic getTopicById(Integer intTopicId);

    public PageInfo<Topic> getHotTopic(int intStart);

    public Boolean userLikeTopic(UserTopic userTopic);

    public Boolean createNewTopic(Integer intCircleId,Integer intUserId,String strTopicContext,String strTopicName);
}

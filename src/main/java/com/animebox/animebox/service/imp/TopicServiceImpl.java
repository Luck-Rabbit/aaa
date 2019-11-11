package com.animebox.animebox.service.imp;

import com.animebox.animebox.bean.circle.Topic;
import com.animebox.animebox.bean.circle.UserTopic;
import com.animebox.animebox.dao.ITopicDao;
import com.animebox.animebox.service.ITopicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName TopicServiceImpl
 * @Description: TODO
 * @Author: LuckyRabbit
 * @create: 2019-10-20 19:53
 * @Version 1.0
 */
@Service
public class TopicServiceImpl implements ITopicService {
    @Autowired
    ITopicDao iTopicDao;

    @Override
    public Topic getTopicById(Integer intTopicId) {
        Topic topic = iTopicDao.selectTopicByTopicId(intTopicId);
        return topic;
    }

    @Override
//    @Cacheable(cacheNames = "circleIndexPage")
    public PageInfo<Topic> getHotTopic(int pageNum) {
        PageHelper.startPage(pageNum,20);
        List<Topic> allHotTopicList = iTopicDao.selectHotTopic();
        PageInfo<Topic> pageInfo = new PageInfo<>(allHotTopicList);
        return pageInfo;
    }

    @Override
    public Boolean userLikeTopic(UserTopic userTopic) {
        if(userTopic.getIntUserLike() == 0){
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String gmt_create=dateFormat.format(new Date());
            String gmt_modified=dateFormat.format(new Date());
            int intInsert = iTopicDao.insertUserLikeTopic(userTopic.getIntTopicId(),userTopic.getIntUserId(),gmt_create,gmt_modified);
            if(intInsert == 1){
                return true;
            }
        }else if(userTopic.getIntUserLike() == 1) {
            int intDelete = iTopicDao.deleteUserLikeTopic(userTopic.getIntTopicId(), userTopic.getIntUserId());
            if(intDelete == 0){
                return true;
            }
        }
        return false;
    }

    /**
     * @Author LuckyRabbit
     * @Description //TODO 两次插入 需要做aop
     * @Date 21:42 2019/10/30
      * @param strTopicContext 
  * @param strTopicName 
     * @return java.lang.Boolean
     **/
    @Override
    public Boolean createNewTopic(Integer intCircleId,Integer intUserId,String strTopicContext,String strTopicName){
//        strTopicContext = strTopicContext.replaceAll("\r\n", "\n");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String gmt_create=dateFormat.format(new Date());
        String gmt_modified=dateFormat.format(new Date());
        int i = iTopicDao.insertNewTopic(intCircleId, intUserId, strTopicContext, strTopicName, gmt_create, gmt_modified);
        if(i>0){
            return true;
        }else {
            return false;
        }
    }
}
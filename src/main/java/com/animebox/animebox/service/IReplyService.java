package com.animebox.animebox.service;

import com.animebox.animebox.bean.circle.Reply;

import java.util.List;

public interface IReplyService {
    public List<Reply> selectTopicReply(Integer intTopicId);
}

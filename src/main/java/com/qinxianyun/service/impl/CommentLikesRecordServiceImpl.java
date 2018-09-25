package com.qinxianyun.service.impl;

import com.qinxianyun.mapper.CommentLikesMapper;
import com.qinxianyun.model.CommentLikesRecord;
import com.qinxianyun.service.CommentLikesRecordService;
import com.qinxianyun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Qinxianyun
 * @Date: 2018/7/12 13:47
 * Describe:
 */
@Service
public class CommentLikesRecordServiceImpl implements CommentLikesRecordService {

    @Autowired
    CommentLikesMapper commentLikesMapper;
    @Autowired
    UserService userService;

    @Override
    public boolean isLiked(long articleId, String originalAuthor, long pId, String username) {
        return commentLikesMapper.isLiked(articleId, originalAuthor, pId, userService.findIdByUsername(username)) != null;
    }

    @Override
    public void insertCommentLikesRecord(CommentLikesRecord commentLikesRecord) {
        commentLikesMapper.insertCommentLikesRecord(commentLikesRecord);
    }
}

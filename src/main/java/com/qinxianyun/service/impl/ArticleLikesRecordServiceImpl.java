package com.qinxianyun.service.impl;

import com.qinxianyun.mapper.ArticleLikesMapper;
import com.qinxianyun.model.ArticleLikesRecord;
import com.qinxianyun.service.ArticleLikesRecordService;
import com.qinxianyun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Qinxianyun
 * @Date: 2018/7/7 15:50
 * Describe:
 */
@Service
public class ArticleLikesRecordServiceImpl implements ArticleLikesRecordService {

    @Autowired
    ArticleLikesMapper articleLikesMapper;
    @Autowired
    UserService userService;

    @Override
    public boolean isLiked(long articleId, String originalAuthor, String username) {
        ArticleLikesRecord articleLikesRecord = articleLikesMapper.isLiked(articleId, originalAuthor, userService.findIdByUsername(username));

        return articleLikesRecord != null;
    }

    @Override
    public void insertArticleLikesRecord(ArticleLikesRecord articleLikesRecord) {
        articleLikesMapper.insertArticleLikesRecord(articleLikesRecord);
    }

}

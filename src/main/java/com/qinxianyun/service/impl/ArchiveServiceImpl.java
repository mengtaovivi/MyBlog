package com.qinxianyun.service.impl;

import com.qinxianyun.mapper.ArchiveMapper;
import com.qinxianyun.service.ArchiveService;
import com.qinxianyun.service.ArticleService;
import com.qinxianyun.utils.TimeUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Qinxianyun
 * @Date: 2018/7/18 12:08
 * Describe:
 */
@Service
public class ArchiveServiceImpl implements ArchiveService {

    @Autowired
    ArchiveMapper archiveMapper;
    @Autowired
    ArticleService articleService;

    @Override
    public JSONObject findArchiveNameAndArticleNum() {
        List<String> archives = archiveMapper.findArchives();
        JSONArray archivesJsonArray = new JSONArray();
        JSONObject archiveJson;
        TimeUtil timeUtil = new TimeUtil();
        for(String archiveName : archives){
            archiveJson = new JSONObject();
            archiveJson.put("archiveName",archiveName);
            archiveName = timeUtil.timeYearToWhippletree(archiveName);
            archiveJson.put("archiveArticleNum",articleService.countArticleArchiveByArchive(archiveName));
            archivesJsonArray.add(archiveJson);
        }
        JSONObject returnJson = new JSONObject();
        returnJson.put("status",200);
        returnJson.put("result", archivesJsonArray);
        return returnJson;
    }

    @Override
    public void addArchiveName(String archiveName) {
        int archiveNameIsExit = archiveMapper.findArchiveNameByArchiveName(archiveName);
        if(archiveNameIsExit == 0){
            archiveMapper.addArchiveName(archiveName);
        }
    }

}

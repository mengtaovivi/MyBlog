package com.qinxianyun.service.impl;

import com.qinxianyun.mapper.TagMapper;
import com.qinxianyun.model.Tag;
import com.qinxianyun.service.TagService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Qinxianyun
 * @Date: 2018/7/16 19:50
 * Describe:
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagMapper tagMapper;

    @Override
    public void addTags(String[] tags, int tagSize) {
        for(String tag : tags){
            if(tagMapper.findIsExitByTagName(tag) == 0){
                Tag t = new Tag(tag, tagSize);
                tagMapper.insertTag(t);
            }
        }
    }

    @Override
    public JSONObject findTagsCloud() {
        List<Tag> tags = tagMapper.findTagsCloud();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",200);
        jsonObject.put("result",JSONArray.fromObject(tags));
        jsonObject.put("tagsNum",tags.size());
        return jsonObject;
    }

    @Override
    public int countTagsNum() {
        return tagMapper.countTagsNum();
    }

    @Override
    public int getTagsSizeByTagName(String tagName) {
        return tagMapper.getTagsSizeByTagName(tagName);
    }
}

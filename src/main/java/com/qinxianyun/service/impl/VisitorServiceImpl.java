package com.qinxianyun.service.impl;

import com.qinxianyun.mapper.VisitorMapper;
import com.qinxianyun.service.VisitorService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Qinxianyun
 * @Date: 2018/6/16 16:21
 * Describe: 访客实现类
 */
@Service
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    VisitorMapper visitorMapper;

    @Override
    public void addVisitorNumByPageName(String pageName, HttpServletRequest request) {

        String visitor;
        if("visitorVolume".equals(pageName)){
            visitor = (String) request.getSession().getAttribute("visitor");
            if(visitor == null){
                visitorMapper.updateVisitorNumByTotalVisitorAndPageName(pageName);
                request.getSession().setAttribute("visitor","yes");
            }else {
                visitorMapper.updateVisitorNumByTotalVisitor();
            }
        } else {
            visitor = (String) request.getSession().getAttribute(pageName);
            if(visitor == null){
                visitorMapper.updateVisitorNumByTotalVisitorAndPageName(pageName);
                request.getSession().setAttribute(pageName, "yes");
            } else {
                visitorMapper.updateVisitorNumByTotalVisitor();
            }
        }

    }

    @Override
    public JSONObject getVisitorNumByPageName(String pageName) {

        long totalVisitor = visitorMapper.getVisitorNumByPageName("totalVisitor");
        long pageVisitor = visitorMapper.getVisitorNumByPageName(pageName);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("totalVisitor", totalVisitor);
        jsonObject.put("pageVisitor", pageVisitor);
        return jsonObject;
    }

    @Override
    public long getNumByPageName(String pageName) {
        return visitorMapper.getVisitorNumByPageName(pageName);
    }

    @Override
    public void insertVisitorArticlePage(String pageName) {
        visitorMapper.insertVisitorArticlePage(pageName);
    }

    @Override
    public long getAllVisitor() {
        return visitorMapper.getAllVisitor();
    }

}

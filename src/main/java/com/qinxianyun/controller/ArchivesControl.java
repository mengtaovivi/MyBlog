package com.qinxianyun.controller;

import com.qinxianyun.service.ArchiveService;
import com.qinxianyun.service.ArticleService;
import com.qinxianyun.utils.TransCodingUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Qinxianyun
 * @Date: 2018/7/18 12:06
 * Describe: 成就Controller
 */
@RestController
public class ArchivesControl {

    @Autowired
    ArchiveService archiveService;
    @Autowired
    ArticleService articleService;

    /**
     * 获得所有归档日期以及每个归档日期的文章数目
     * @return
     */
    @GetMapping("/findArchiveNameAndArticleNum")
    public JSONObject findArchiveNameAndArticleNum(){
        return archiveService.findArchiveNameAndArticleNum();
    }


    /**
     * 分页获得该归档日期下的文章
     * @param archive
     * @param request
     * @return
     */
    @GetMapping("/getArchiveArticle")
    public JSONObject getArchiveArticle(@RequestParam("archive") String archive,
                                        HttpServletRequest request){
        try {
            archive = TransCodingUtil.unicodeToString(archive);
        } catch (Exception e){
        }
        int rows = Integer.parseInt(request.getParameter("rows"));
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        return articleService.findArticleByArchive(archive, rows, pageNum);
    }
}

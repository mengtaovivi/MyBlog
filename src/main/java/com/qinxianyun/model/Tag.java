package com.qinxianyun.model;

import lombok.Data;

/**
 * @author: Qinxianyun
 * @Date: 2018/6/20 15:36
 * Describe: 标签
 */
@Data
public class Tag {

    private int id;

    /**
     * 标签名
     */
    private String tagName;

    /**
     * 标签大小
     */
    private int tagSize;

    public Tag() {
    }

    public Tag(String tagName, int tagSize) {
        this.tagName = tagName;
        this.tagSize = tagSize;
    }
}

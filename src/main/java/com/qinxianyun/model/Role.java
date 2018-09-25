package com.qinxianyun.model;

import lombok.Data;

/**
 * @author: Qinxianyun
 * @Date: 2018/6/5 19:41
 * Describe: 权限
 */
@Data
public class Role {

    private int id;

    private String name;

    public Role(){

    }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

package com.qinxianyun.repository.mybatis;

import com.qinxianyun.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author: Qinxianyun
 * @Date: 2018/6/5 19:37
 * Describe:xml配置User表操作
 */
@Repository
public interface UserRepository {

    /**
     *  通过手机号查找用户
     * @param phone 手机号
     * @return 用户
     */
    User findByPhone(String phone);

}

package com.qinxianyun.controller.register;

import com.qinxianyun.model.Password;
import com.qinxianyun.model.User;
import com.qinxianyun.service.UserService;
import com.qinxianyun.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Qinxianyun
 * @Date: 2018/6/4 11:48
 * Describe: 注册Controller
 */
@Controller
public class RegisterControl {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public String register(User user,
                            HttpServletRequest request){

        String authCode = request.getParameter("authCode");

        String trueMsgCode = (String) request.getSession().getAttribute("trueMsgCode");

        //检测手机验证码
        if(!authCode.equals(trueMsgCode)){
            return "0";
        }
        if(userService.usernameIsExit(user.getUsername())){
            return "3";
        }
        Password password = new Password();
        password.setPassword(user.getPassword());
        password.setUsername(user.getUsername());
        //注册时对密码进行MD5加密
        MD5Util md5Util = new MD5Util();
        user.setPassword(md5Util.encode(user.getPassword()));
        return userService.insert(user);
    }

}

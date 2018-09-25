package com.qinxianyun.service.security;

import com.qinxianyun.model.Role;
import com.qinxianyun.model.User;
import com.qinxianyun.repository.mybatis.UserRepository;
import com.qinxianyun.service.UserService;
import com.qinxianyun.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Qinxianyun
 * @Date: 2018/6/5 19:11
 * Describe: 用户登录处理
 */
@Service
public class CustomUserService implements UserDetailsService{

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {

        User user = userRepository.findByPhone(phone);

        if(user == null){
            return (UserDetails) new UsernameNotFoundException("用户不存在");
        }

        TimeUtil timeUtil = new TimeUtil();
        String recentlyLanded = timeUtil.getFormatDateForSix();
        userService.updateRecentlyLanded(user.getUsername(), recentlyLanded);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for(Role role : user.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        System.out.println(phone + "用户登录成功");
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}

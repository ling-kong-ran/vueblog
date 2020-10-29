package com.ling.vblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ling.vblog.entity.User;
import com.ling.vblog.mapper.UserMapper;
import com.ling.vblog.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 凌孔燃
 * @since 2020-10-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void register(User user) {
        //查看用户是否已存在
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("username",user.getUsername());
        User selectOne = userMapper.selectOne(wrapper);
            if (selectOne!=null){
                throw new RuntimeException("用户已存在");
            }else{
                String password = new Md5Hash(user.getPassword(), user.getUsername(), 1024).toHex();
                user.setPassword(password);
                user.setDeleted(false);
                userMapper.insert(user);
            }



    }
}

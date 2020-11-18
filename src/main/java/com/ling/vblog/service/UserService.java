package com.ling.vblog.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ling.vblog.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 凌孔燃
 * @since 2020-10-24
 */
public interface UserService extends IService<User> {
    void register(User user);
}

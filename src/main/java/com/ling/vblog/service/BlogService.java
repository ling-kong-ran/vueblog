package com.ling.vblog.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ling.vblog.entity.Blog;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 凌孔燃
 * @since 2020-10-24
 */
public interface BlogService extends IService<Blog> {
    List<Blog> selectPage(Integer currentPage, Integer size);
}

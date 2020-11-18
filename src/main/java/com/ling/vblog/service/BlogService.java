package com.ling.vblog.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ling.vblog.dto.PageDto;
import com.ling.vblog.entity.Blog;
import com.ling.vblog.entity.BlogTags;
import org.apache.ibatis.annotations.Param;

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
    Blog getOneBlog( Integer id);

    void add(Blog blog);

    void update(Blog blog);

    void delete(Integer id);

    PageDto pages(int i, Integer currentPage);
}

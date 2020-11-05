package com.ling.vblog.controller;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ling.vblog.dto.PageDto;
import com.ling.vblog.entity.AjaxResult;
import com.ling.vblog.entity.Blog;
import com.ling.vblog.service.BlogService;
import com.ling.vblog.utils.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 凌孔燃
 * @since 2020-10-28
 */
@RestController
public class BlogController {
    @Autowired
    BlogService blogService;

    @GetMapping("/blogs")
    public AjaxResult list(@RequestParam(defaultValue = "1") Integer currentPage) {
        int size = 5;//size也可以根据前端传来的size进行更改
        List<Blog> blogs = blogService.selectPage(currentPage == 1 ? 0 : size * (currentPage - 1), size);
        PageDto page = PageDto.getPage(blogService.count(), currentPage, size, blogs);
        return AjaxResult.success(page);
    }

    @GetMapping("/blog/{id}")//select
    public AjaxResult select(@PathVariable(name = "id") Integer id) {
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "该博客已被删除");
        blogService.updateView(id);//更新访问次数
        return AjaxResult.success(blog);
    }

    @RequiresAuthentication
    @PutMapping("/blog")//update
    public AjaxResult update(@Valid @RequestBody Blog blog) {
        Blog temp = blogService.getById(blog.getId());
        Assert.isTrue(Objects.equals(temp.getUserId(), ShiroUtils.getProfile().getId()), "没有权限编辑");
        blogService.updateById(blog);
        return AjaxResult.success();
    }

    @RequiresAuthentication
    @DeleteMapping("/blog/{id}")//delete
    public AjaxResult delete(@PathVariable(name = "id") Integer id) {
        blogService.removeById(id);
        return AjaxResult.success();
    }

    @RequiresAuthentication
    @PostMapping("/blog")//add
    public AjaxResult add(@Valid @RequestBody Blog blog) {
        blog.setDeleted(false);
        blog.setUserId(ShiroUtils.getProfile().getId());
        blog.setViews(1);
        blogService.save(blog);
        return AjaxResult.success();
    }

}


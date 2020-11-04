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
    public AjaxResult list(@RequestParam(defaultValue = "1") Integer currentPage){
        return AjaxResult.success(getPage(currentPage,5));
    }

    @GetMapping("/blog/{id}")//select
    public AjaxResult select(@PathVariable(name = "id") Integer id){
        Blog blog = blogService.getById(id);
        Assert.notNull(blog,"该博客已被删除");
        blogService.updateView(id);//更新访问次数
        return AjaxResult.success(blog);
    }
    @RequiresAuthentication
    @PutMapping("/blog")//update
    public AjaxResult update(@Valid @RequestBody Blog blog){
        Blog temp = blogService.getById(blog.getId());
        Assert.isTrue(Objects.equals(temp.getUserId(), ShiroUtils.getProfile().getId()),"没有权限编辑");
        blogService.updateById(blog);
        return AjaxResult.success();
    }

    @RequiresAuthentication
    @DeleteMapping("/blog/{id}")//delete
    public AjaxResult delete(@PathVariable(name = "id") Integer id){
        blogService.removeById(id);
        return AjaxResult.success();
    }

    @RequiresAuthentication
    @PostMapping("/blog")//add
    public AjaxResult add(@Valid @RequestBody Blog blog){
        blog.setDeleted(false);
        blog.setUserId(ShiroUtils.getProfile().getId());
        blog.setViews(1);
        blogService.saveOrUpdate(blog);
        return AjaxResult.success();
    }

    private PageDto getPage(Integer currentPage,Integer size){
        int count = blogService.count();
        if (currentPage<=0){
            currentPage=0;
        }
        int search=size*(currentPage-1);
        // 1:0 2:5 (currentPage-1)*size
        //1:0 2:
        Integer total=count/size==1?2:count/size;
        List<Blog> blogs= blogService.selectPage(search==0?1:search, size);
        return new PageDto(total,currentPage,size,blogs);
        //Page<Blog> page = new Page<>(currentPage,5);
        //Page<Blog> views = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("views"));
    }
}


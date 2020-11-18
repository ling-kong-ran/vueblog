package com.ling.vblog.controller;


import com.ling.vblog.dto.PageDto;
import com.ling.vblog.entity.AjaxResult;
import com.ling.vblog.entity.Blog;
import com.ling.vblog.service.BlogService;
import com.ling.vblog.service.BlogTagsService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 凌孔燃
 * @since 2020-10-28
 */
@RestController
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    BlogTagsService blogTagsService;

    @GetMapping("/blogs")
    public AjaxResult list(@RequestParam(defaultValue = "1") Integer currentPage) {
        PageDto pageDto = blogService.pages(5, currentPage);
        return AjaxResult.success(pageDto);
    }

    @GetMapping("/blog/{id}")//select
    public AjaxResult select(@PathVariable(name = "id") Integer id) {
        Blog blog = blogService.getOneBlog(id);
        return AjaxResult.success(blog);
    }

    @RequiresAuthentication
    @PutMapping("/blog")//update
    public AjaxResult update(@Valid @RequestBody Blog blog) {
        blogService.update(blog);
        return AjaxResult.success();
    }

    @RequiresAuthentication
    @DeleteMapping("/blog/{id}")//delete
    public AjaxResult delete(@PathVariable(name = "id") Integer id) {
        blogService.delete(id);
        return AjaxResult.success();
    }

    @RequiresAuthentication
    @PostMapping("/blog")//add
    public AjaxResult add(@Valid @RequestBody Blog blog) {
        blogService.add(blog);
        return AjaxResult.success();
    }

}


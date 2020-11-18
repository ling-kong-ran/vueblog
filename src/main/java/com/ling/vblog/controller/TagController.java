package com.ling.vblog.controller;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ling.vblog.entity.AjaxResult;
import com.ling.vblog.entity.Tag;
import com.ling.vblog.entity.Type;
import com.ling.vblog.service.TagService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 凌孔燃
 * @since 2020-10-28
 */
@RestController

public class TagController {
    @Autowired
    private TagService tagService;

    //@RequiresAuthentication

    @GetMapping("tags")
    public AjaxResult list(){
        return AjaxResult.success(tagService.list());
    }
    @GetMapping("tag/{id}")
    public AjaxResult select(@PathVariable(name = "id") Integer id) {
        Tag tag = tagService.getById(id);
        Assert.notNull(tag,"该标签已删除");
        return AjaxResult.success(tag);
    }
    @RequiresAuthentication
    @PostMapping("tag")
    public AjaxResult add(@Valid @RequestBody Tag tag) {
        tagService.save(tag);
        return AjaxResult.success();
    }

    @RequiresAuthentication
    @DeleteMapping("tag/{id}")
    public AjaxResult delete(@PathVariable(name = "id") Integer id) {
            tagService.removeById(id);
            return AjaxResult.success();
    }

    @RequiresAuthentication
    @PutMapping("tag")
    public AjaxResult update(@Valid @RequestBody Tag tag) {
        try {
            tagService.updateById(tag);
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.error();
        }
    }


}


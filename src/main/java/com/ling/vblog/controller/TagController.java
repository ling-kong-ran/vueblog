package com.ling.vblog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ling.vblog.entity.AjaxResult;
import com.ling.vblog.entity.Tag;
import com.ling.vblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 凌孔燃
 * @since 2020-10-28
 */
@RestController
@RequestMapping("/blog/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    //@RequiresAuthentication
    @GetMapping("test")
    public AjaxResult test(){
        return AjaxResult.success();
    }

    @PostMapping("add")
    public AjaxResult add(@Valid @RequestBody Tag tag) {
        try {
            QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name", tag.getName());
            Tag one = tagService.getOne(queryWrapper);
            if (ObjectUtils.isEmpty(one)) {
                tag.setDeleted(false);
                tagService.save(tag);
                return AjaxResult.success();
            } else {
                throw new RuntimeException("该标签已存在");
            }
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }


    }

    @DeleteMapping
    public AjaxResult del(Integer id) {
        try {
            tagService.removeById(id);
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.error();
        }
    }


    @PutMapping
    public AjaxResult update(@Valid @RequestBody Tag tag) {
        try {
            tagService.updateById(tag);
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.error();
        }
    }

    @GetMapping("getPage")
    public List<Tag> all(Integer current){
        int size = 1;
        current--;
        int count = tagService.count();
        if (current<0){
            current=0;
        }
        if (current>count/size){
            current=count/size;
        }
        Integer total=count/size;
        System.out.println(total);
        return tagService.selectPage(current, size);
    }
}


package com.ling.vblog.controller;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ling.vblog.entity.AjaxResult;
import com.ling.vblog.entity.Type;
import com.ling.vblog.service.TypeService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 凌孔燃
 * @since 2020-10-28
 */
@RestController
public class TypeController {
    @Autowired
    private TypeService typeService;

    //@RequiresAuthentication

    @GetMapping("types")
    public AjaxResult list(){
        return AjaxResult.success(typeService.list());
    }

    @GetMapping("type/{id}")
    public AjaxResult select(@PathVariable(name = "id") Integer id) {
        Type type = typeService.getById(id);
        Assert.notNull(type,"该类型已删除");
        return AjaxResult.success(type);
    }
    @RequiresAuthentication
    @PostMapping("type")
    public AjaxResult add(@RequestBody Type type) {
        type.setDeleted(false);
        typeService.save(type);
        return AjaxResult.success();
    }
    @RequiresAuthentication
    @DeleteMapping("type/{id}")
    public AjaxResult delete(@PathVariable(name = "id") Integer id) {
        typeService.removeById(id);
        return AjaxResult.success();
    }

    @RequiresAuthentication
    @PutMapping("type")
    public AjaxResult update(@RequestBody Type type) {
        try {
            typeService.updateById(type);
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.error();
        }
    }
}


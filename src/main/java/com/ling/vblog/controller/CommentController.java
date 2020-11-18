package com.ling.vblog.controller;


import cn.hutool.core.bean.BeanUtil;
import com.ling.vblog.dto.CommentDto;
import com.ling.vblog.entity.AjaxResult;
import com.ling.vblog.entity.Comment;
import com.ling.vblog.service.CommentService;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 凌孔燃
 * @since 2020-10-28
 */
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("comment")
    public AjaxResult add(@RequestBody Comment comment) {
        commentService.save(comment);
        return AjaxResult.success();
    }

    @PostMapping("comment/comment")
    public AjaxResult addSon(@RequestBody Comment comment) {
        //提交子评论的时候，会将原本单一评论给逻辑删除
        commentService.removeById(comment.getParentCommentId());
        commentService.save(comment);
        return AjaxResult.success();
    }

    @PutMapping("comment")
    public AjaxResult update(@RequestBody Comment comment) {
        commentService.updateById(comment);
        return AjaxResult.success();
    }

    @GetMapping("comment/{id}")
    public AjaxResult get(@PathVariable(name = "id") Integer blogId) {
        return AjaxResult.success(commentService.getBlogComment(blogId));
    }

    @DeleteMapping("comment/{id}")
    public AjaxResult del(@PathVariable(name = "id") Integer id) {
        commentService.removeById(id);
        return AjaxResult.success();
    }

}


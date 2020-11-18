package com.ling.vblog.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ling.vblog.dto.CommentDto;
import com.ling.vblog.entity.Comment;
import com.ling.vblog.mapper.CommentMapper;
import com.ling.vblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 凌孔燃
 * @since 2020-10-24
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<CommentDto> getBlogComment(Integer blogId) {
        //获取某个博文的评论和其子评论
        List<Comment> comments = commentMapper.getByBlogId(blogId);
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment a : comments
        ) {
            CommentDto commentDto = new CommentDto();
            BeanUtil.copyProperties(a, commentDto);
            commentDtos.add(commentDto);
        }
        for (CommentDto a: commentDtos
             ) {
            //子评论的筛选
            List<Comment> d = new ArrayList<>();
            for (Comment c:comments
            ) {
                //将子评论放入父评论中
               if (a.getParentCommentId()==null&&c.getParentCommentId()!=null&&c.getParentCommentId().equals(a.getId())){
                   d.add(c);
               }
            }
            if (d.size()>0){
                a.setComment_son(d);
            }
        }
        //去重
        commentDtos.removeIf(e -> e.getParentCommentId() != null);

        return commentDtos;
    }
}

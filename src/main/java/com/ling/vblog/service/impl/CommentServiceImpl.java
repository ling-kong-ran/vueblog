package com.ling.vblog.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ling.vblog.entity.Comment;
import com.ling.vblog.mapper.CommentMapper;
import com.ling.vblog.service.CommentService;
import org.springframework.stereotype.Service;

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

}

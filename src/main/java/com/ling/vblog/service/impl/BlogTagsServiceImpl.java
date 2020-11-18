package com.ling.vblog.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ling.vblog.entity.BlogTags;
import com.ling.vblog.mapper.BlogTagsMapper;
import com.ling.vblog.service.BlogTagsService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BlogTagsServiceImpl extends ServiceImpl<BlogTagsMapper, BlogTags> implements BlogTagsService {
    @Autowired
    BlogTagsMapper blogTagsMapper;

}

package com.ling.vblog.service.impl;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ling.vblog.dto.PageDto;
import com.ling.vblog.entity.Blog;
import com.ling.vblog.entity.Tag;
import com.ling.vblog.mapper.BlogMapper;
import com.ling.vblog.mapper.BlogTagsMapper;
import com.ling.vblog.service.BlogService;
import com.ling.vblog.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 凌孔燃
 * @since 2020-10-24
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private BlogTagsMapper blogTagsMapper;

    @Override
    public Blog getOneBlog(Integer id) {
        blogMapper.updateView(id);//更新访问次数
        Blog oneBlog = blogMapper.getOneBlog(id);
        Assert.notNull(oneBlog, "该博客已被删除");
        return oneBlog;
    }

    @Override
    public void add(Blog blog) {
        blogMapper.insert(blog);
        Blog b = blogMapper.getByTitle(blog.getTitle());
        blog.setUserId(ShiroUtils.getProfile().getId());
        blog.setViews(1);
        List<Tag> s= new ArrayList<>();
        for (Tag tag : blog.getTags()) {
            tag.setBlogsId(b.getId());
            s.add(tag);
        }
        blogTagsMapper.addBlogTags(s);
    }

    @Override
    public void update(Blog blog) {
        Blog temp = blogMapper.selectById(blog.getId());
        Assert.isTrue(Objects.equals(temp.getUserId(), ShiroUtils.getProfile().getId()), "没有权限编辑");
        blogMapper.updateById(blog);
        List<Tag> s= new ArrayList<>();
        blog.getTags().forEach(r->{
            r.setBlogsId(blog.getId());
            s.add(r);
        });
        blogTagsMapper.deleteBlogTags(blog.getId());
        blogTagsMapper.addBlogTags(s);
    }

    @Override
    public void delete(Integer id) {
        blogMapper.deleteById(id);
        blogTagsMapper.deleteBlogTags(id.longValue());
    }

    @Override
    public PageDto pages(int size, Integer currentPage) {
        List<Blog> blogs = blogMapper.selectPage(currentPage == 1 ? 0 : size * (currentPage - 1), size);
        return PageDto.getPage(blogMapper.count(), currentPage, size, blogs);

    }


}
